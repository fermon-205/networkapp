package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.event.MouseEvents;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;

@UIScope
public class Logo extends Image {

    @Inject
    public Logo(final DeviceSelector deviceSelector, final InterfaceSelector interfaceSelector) {
        super(null, new ThemeResource("img/tpac_logo.png"));
        addClickListener(new MouseEvents.ClickListener() {
                             @Override
                             public void click(MouseEvents.ClickEvent e) {
                                 deviceSelector.select(null);
                                 interfaceSelector.select("");
                                 UI.getCurrent().getNavigator().navigateTo("");
                             }
                         }
        );
    }
}
