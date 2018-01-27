package com.mybatis.controller.distributed;

import com.google.gson.Gson;
import com.mybatis.api.APIUserService;
import com.response.ErrorCode;
import com.response.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yunkai on 2017/9/19.
 */
@RestController
@RequestMapping("/distributed")
public class DistributedController {

    private static final Logger logger = LoggerFactory.getLogger(DistributedController.class);

    private final String TOPIC_EXCHANGE = "topicExchange";

    private final String ROUTING_KEY = "topic.two";

    @Autowired
    private APIUserService apiUserService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/save")
    public RpcResult<Integer> save(String userName, String userPhone){
        RpcResult<Integer> result = null;
        try {
            result = apiUserService.saveUser(userName, userPhone);
            //模拟rpc服务请求后出现错误
            int a = 1 / 0;
            return result;
        }catch (Exception e){
            logger.error("error : {}", e);
            //当出现错误时，通过rabbitmq消息，回滚数据
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY, new Gson().toJson(result.getValue()));
        }
        return RpcResult.ofError(ErrorCode.BIZ_ERROR.getCode(), ErrorCode.BIZ_ERROR.getMsg("新增用户"));
    }
}
