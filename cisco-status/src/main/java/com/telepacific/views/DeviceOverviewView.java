package com.telepacific.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;

@GuiceView(name="")
public class DeviceOverviewView extends Label implements View {

    public DeviceOverviewView(){
        super("device overview goes here");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
