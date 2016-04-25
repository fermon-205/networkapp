package com.telepacific.login;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.telepacific.api.LoginApi;
import com.telepacific.bus.LoginEvent;
import com.telepacific.bus.LogoutEvent;
import com.telepacific.bus.UIEventBus;
import com.telepacific.domain.User;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.server.VaadinSession;

@UIScope
public class LoginApiImpl implements LoginApi {

    @Inject
    private UIEventBus uiEventBus;

    @Override
    public boolean login(String userName, String password) {
        if(!"user".equals(userName) || !"secret".equals(password)){
            return false;
        }

        User user = new User(userName, password);

        VaadinSession.getCurrent().setAttribute(User.class, user);

        uiEventBus.post(new LoginEvent(user));

        return true;
    }

    @Override
    public Optional<User> getCurrentUser() {
        return Optional.fromNullable(VaadinSession.getCurrent().getAttribute(User.class));
    }

    @Override
    public void logout() {
        VaadinSession.getCurrent().setAttribute(User.class, null);

        uiEventBus.post(new LogoutEvent());
    }
}
