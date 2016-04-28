package com.telepacific.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;

@GuiceView(name = "some view")
public class AnotherView extends Label implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setValue("new view");
    }
}
