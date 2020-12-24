package com.zx.service;

import com.zx.po.User;

public interface UserService {

    // 验证用户名和密码
    User checkUser(String username,String password);

}
