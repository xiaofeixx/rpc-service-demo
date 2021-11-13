package com.rpc.user.userservice.rest;

import com.rpc.user.userservice.entity.User;
import com.rpc.user.userservice.service.UserService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;

/**
 * @author xiaofeixx
 */
@Controller
public class UserRestServiceIml implements UserService.Iface {

    @Override
    public User getUserById(long id) throws TException {
        return null;
    }

    @Override
    public User getUserByLoginName(String loginName) throws TException {
        User user = new User();
        user.setLoginName("xiaofei");
        user.setId(1L);
        user.setPassword("123456");
        if ("xiaofei".equals(loginName)) {
            return user;
        }
        return null;
    }

    @Override
    public String getUserNameByLoginName(String loginName) throws TException {
        return null;
    }

    @Override
    public String getUserNameById(long id) throws TException {
        return null;
    }
}
