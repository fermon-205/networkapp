package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
public class Application extends VerticalLayout {

    @Inject
    public Application(Header header, Content content) {
        addComponents(header, content);
        setExpandRatio(content, 1);
        setSizeFull();
    }
}
