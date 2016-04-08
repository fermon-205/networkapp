package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;

@UIScope
public class Logo extends Image {

    @Inject
    public Logo(DeviceSelector deviceSelector, InterfaceSelector interfaceSelector) {
        super(null, new ThemeResource("img/tpac_logo.png"));
        addClickListener(e -> {
                deviceSelector.select(null);
                interfaceSelector.select(null);
                UI.getCurrent().getNavigator().navigateTo("");
            }
        );
    }
}
