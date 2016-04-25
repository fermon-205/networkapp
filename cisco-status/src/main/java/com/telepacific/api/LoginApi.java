package com.telepacific.api;

import com.google.common.base.Optional;

import com.telepacific.domain.User;

public interface LoginApi {

    boolean login(String userName, String password);
    Optional<User> getCurrentUser();
    void logout();
}
