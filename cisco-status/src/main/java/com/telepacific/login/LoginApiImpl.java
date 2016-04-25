package com.telepacific.login;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import com.telepacific.api.LoginApi;
import com.telepacific.bus.LoginEvent;
import com.telepacific.bus.LogoutEvent;
import com.telepacific.bus.UIEventBus;
import com.telepacific.domain.User;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.server.VaadinSession;

import javax.persistence.EntityManager;

@UIScope
public class LoginApiImpl implements LoginApi {

    @Inject
    private UIEventBus uiEventBus;

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    @Override
    public boolean login(String userName, String password) {

        final EntityManager entityManager = entityManagerProvider.get();

        final User user = entityManager.find(User.class, userName);

        if(user == null){
            return false;
        }

        if(!password.equals(user.getPassword())){
            return false;
        }

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
