package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

@UIScope
public class Header extends HorizontalLayout {

    @Inject
    public Header(SelectorsLayout selectorsLayout, Logo logo){
        addComponents(selectorsLayout, logo);
        setComponentAlignment(selectorsLayout, Alignment.TOP_LEFT);
        setComponentAlignment(logo, Alignment.TOP_RIGHT);
        setWidth("100%");
        setHeight("56px");
    }
}
