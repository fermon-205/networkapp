package com.telepacific.bus;

import com.telepacific.domain.User;

public class LoginEvent {

    private final User user;

    public LoginEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
