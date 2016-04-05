package com.telepacific;

import com.google.inject.Inject;

import javax.servlet.annotation.WebServlet;

import com.telepacific.api.Cisco;
import com.telepacific.components.Application;
import com.telepacific.components.Content;
import com.telepacific.components.DeviceSelector;
import com.telepacific.components.Header;
import com.telepacific.components.InterfaceSelector;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.guice.annotation.Configuration;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.guice.annotation.ViewContainer;
import com.vaadin.guice.server.GuiceVaadinServlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.telepacific.MyAppWidgetset")
@GuiceUI
public class MyUI extends UI {

    @Inject
    private Application application;

    @Inject
    @ViewContainer
    private Content content;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(application);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    @Configuration(modules = {}, basePackages = "com.telepacific")
    public static class MyUIServlet extends GuiceVaadinServlet {
    }
}
