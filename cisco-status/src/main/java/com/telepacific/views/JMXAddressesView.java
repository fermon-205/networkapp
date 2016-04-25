package com.telepacific.views;

import com.google.inject.Inject;

import com.tailf.cdb.Cdb;
import com.telepacific.components.DeviceSelector;
import com.telepacific.components.JMXAddressesGrid;
import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@GuiceView(name = "jmx-addresses-view")
public class JMXAddressesView extends VerticalLayout implements View {

    //@Inject
    //Cdb cdb;

    private final DeviceSelector deviceSelector;

    private final Label topLabel = new Label();

    @Inject
    public JMXAddressesView(JMXAddressesGrid jmxAddressesGrid, DeviceSelector deviceSelector) {
        this.deviceSelector = deviceSelector;
        setSizeFull();
        addComponents(topLabel, jmxAddressesGrid);
        setExpandRatio(jmxAddressesGrid, 1);
        setMargin(true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        topLabel.setValue("jmx-addresses for " + deviceSelector.getValue());


    }
}
