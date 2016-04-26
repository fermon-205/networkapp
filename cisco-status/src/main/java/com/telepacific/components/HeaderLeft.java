package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@UIScope
public class HeaderLeft extends VerticalLayout {

    @Inject
    HeaderLeft(LoginStatus loginStatus, SelectorsLayout selectorsLayout){
        addComponents(loginStatus, selectorsLayout);
        setComponentAlignment(loginStatus, Alignment.MIDDLE_LEFT);
        setComponentAlignment(selectorsLayout, Alignment.TOP_LEFT);
        //setExpandRatio(selectorsLayout,1);
        setHeight("56px");
        addStyleName("lftmrgn");
    }
}
