package com.telepacific.components;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;

@UIScope
public class Logo extends Image {

    public Logo(){
        super(null, new ExternalResource("https://webmail.telepacific.net/imgs/tpac_logo.jpg"));
    }
}
