package com.telepacific;

import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import com.telepacific.api.LoginApi;
import com.telepacific.bus.LoginEvent;
import com.telepacific.bus.LogoutEvent;
import com.telepacific.bus.UIEventBus;
import com.telepacific.components.Application;
import com.telepacific.components.Content;
import com.telepacific.domain.User;
import com.telepacific.login.LoginWindow;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.guice.annotation.ViewContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("mytheme")
@Widgetset("com.telepacific.MyAppWidgetset")
@GuiceUI
public class CiscoStatusUI extends UI {

    @Inject
    private Application application;

    @Inject
    @ViewContainer
    @SuppressWarnings("unused")
    private Content content;

    @Inject
    private LoginApi loginApi;

    @Inject
    private LoginWindow loginWindow;

    @Inject
    private UIEventBus uiEventBus;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(application);

        uiEventBus.register(this);

        final Optional<User> currentUser = loginApi.getCurrentUser();

        if (currentUser.isPresent()) {
            uiEventBus.post(new LoginEvent(currentUser.get()));
        } else {
            uiEventBus.post(new LogoutEvent());
        }
    }

    @Subscribe
    public void onLogout(LogoutEvent logoutEvent){
        addWindow(loginWindow);
    }
}
