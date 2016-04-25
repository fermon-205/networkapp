package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@UIScope
public class LoginStatus extends HorizontalLayout{

    @Inject
    LoginStatus(CurrentUserLabel currentUserLabel, LogoutLink logoutLink){
        addComponents(currentUserLabel, logoutLink);
        setSpacing(true);
    }
}
