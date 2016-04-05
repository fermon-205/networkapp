package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

@UIScope
public class Header extends HorizontalLayout {

    @Inject
    public Header(DeviceSelector deviceSelector, InterfaceSelector interfaceSelector, Logo logo){
        setMargin(true);

        HorizontalLayout selectorLayout = new HorizontalLayout();
        selectorLayout.setSpacing(true);
        selectorLayout.addComponents(deviceSelector, interfaceSelector);
        selectorLayout.setComponentAlignment(deviceSelector, Alignment.TOP_LEFT);
        selectorLayout.setComponentAlignment(interfaceSelector, Alignment.TOP_LEFT);

        addComponents(selectorLayout, logo);
        setComponentAlignment(selectorLayout, Alignment.TOP_LEFT);
        setComponentAlignment(logo, Alignment.TOP_RIGHT);
        setWidth("100%");
    }
}
