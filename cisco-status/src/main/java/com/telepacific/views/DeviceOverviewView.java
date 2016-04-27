package com.telepacific.views;

import com.google.inject.Inject;

import com.telepacific.data.VLanContainer;
import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@GuiceView(name = "")
public class DeviceOverviewView extends VerticalLayout implements View {

    @Inject
    public DeviceOverviewView(VLanContainer container) {

        Label label = new Label("all available VLans");

        Grid grid = new Grid(container);

        addComponents(label, grid);

        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
