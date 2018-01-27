package com.mybatis.api;


import com.response.RpcResult;

/**
 * 对外api
 *
 * Created by yunkai on 2018/1/27.
 */
public interface APIUserService {

    /**
     * 新增用户
     *
     * @param userName  用户名称
     * @param userPhone  用户联系方式
     * @return
     */
    public RpcResult<Integer> saveUser(String userName, String userPhone);
}
