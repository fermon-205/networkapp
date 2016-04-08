package com.telepacific;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.guice.annotation.Configuration;
import com.vaadin.guice.server.GuiceVaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "CiscoStatusUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = CiscoStatusUI.class, productionMode = false)
@Configuration(modules = {}, basePackages = "com.telepacific")
public class CiscoStatusUIServlet extends GuiceVaadinServlet{
}
