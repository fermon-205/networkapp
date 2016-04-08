package com.telepacific.components;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;

@UIScope
public class Logo extends Image {

    public Logo() {
        super(null, new ThemeResource("img/tpac_logo.png"));
    }
}
