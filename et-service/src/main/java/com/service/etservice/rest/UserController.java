package com.service.etservice.rest;

import com.service.etservice.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13465
 */
@RestController
public class UserController {

    @Autowired
    private UserRpcClient client;

    @RequestMapping("login")
    public String login(String username, @NonNull String password) {
        if (StringUtils.isBlank(username)) {
            return "不行了，你不是我们的一员";
        }
        User user;
        UserService.Client client = this.client.getClient();

        try {
            this.client.open();
            user = client.getUserByLoginName(username);
        } catch (TException e) {
           return "奇怪，你的输入导致报错了";
        } finally {
            this.client.close();
        }
        if (null == user) {
            return "不行了，你不是我们的一员";
        }
        if (password.equals(user.password)) {
            return "可以啊，小伙子";
        }
        return "密码不对";
    }




}
