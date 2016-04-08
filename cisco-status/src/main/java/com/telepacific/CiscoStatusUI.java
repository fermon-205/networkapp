package com.telepacific;

import com.google.inject.Inject;

import com.telepacific.components.Application;
import com.telepacific.components.Content;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.guice.annotation.ViewContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("mytheme")
@Widgetset("com.telepacific.MyAppWidgetset")
@GuiceUI
public class CiscoStatusUI extends UI {

    @Inject
    private Application application;

    @Inject
    @ViewContainer
    @SuppressWarnings("unused")
    private Content content;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(application);
    }
}
