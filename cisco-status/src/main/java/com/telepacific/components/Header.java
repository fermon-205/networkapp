package com.telepacific.components;

import com.google.inject.Inject;

import com.telepacific.login.LoginWindow;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

@UIScope
public class Header extends HorizontalLayout {

    @Inject
    public Header(HeaderLeft headerLeft, Logo logo) {
        addComponents(headerLeft, logo);
        setComponentAlignment(headerLeft, Alignment.TOP_LEFT);
        setComponentAlignment(logo, Alignment.TOP_RIGHT);
        setWidth("100%");
        setHeight("56px");
    }
}
