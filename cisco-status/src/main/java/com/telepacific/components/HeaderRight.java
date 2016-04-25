package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;

import static com.vaadin.ui.Alignment.*;

@UIScope
public class HeaderRight extends HorizontalLayout {

    @Inject
    HeaderRight(CurrentUserLabel currentUserLabel, Logo logo){
        addComponents(currentUserLabel, logo);
        setComponentAlignment(currentUserLabel, MIDDLE_RIGHT);
        setComponentAlignment(logo, TOP_RIGHT);
    }
}
