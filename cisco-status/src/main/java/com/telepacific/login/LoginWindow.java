package com.telepacific.login;

import com.google.inject.Inject;

import com.telepacific.api.LoginApi;
import com.telepacific.bus.UIEventBus;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.*;

@UIScope
public class LoginWindow extends Window{

    @Inject
    private UIEventBus uiEventBus;

    @Inject
    LoginWindow(final LoginApi loginApi){

        final LoginForm loginForm = new LoginForm();

        loginForm.setStyleName("login-form");

        loginForm.addLoginListener(new LoginForm.LoginListener() {
            @Override
            public void onLogin(LoginForm.LoginEvent event) {
                if(loginApi.login(event.getLoginParameter("username"), event.getLoginParameter("password"))){
                    close();
                } else {
                    Notification.show("wrong username/password");
                }
            }
        });

        setContent(loginForm);
        setCaption("Login required");

        setClosable(false);
        setDraggable(false);
        setResizable(false);
        setModal(true);
        setWidth("200px");
        setHeight("200px");
    }
}
