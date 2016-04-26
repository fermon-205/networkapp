package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

@UIScope
public class SelectorsLayout extends HorizontalLayout {

    @Inject
    SelectorsLayout(DeviceSelector deviceSelector, InterfaceSelector interfaceSelector) {
        addComponents(deviceSelector, interfaceSelector);
        setComponentAlignment(deviceSelector, Alignment.MIDDLE_LEFT);
        setComponentAlignment(interfaceSelector, Alignment.MIDDLE_LEFT);
        setSpacing(true);
    }
}
