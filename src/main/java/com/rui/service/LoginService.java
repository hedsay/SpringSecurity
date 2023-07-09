package com.rui.service;

import com.rui.domain.ResponseResult;
import com.rui.domain.User;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();

}
