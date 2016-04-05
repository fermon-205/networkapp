package com.telepacific.views;

import com.google.inject.Inject;

import com.telepacific.components.JMXAddressesGrid;
import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Panel;

@GuiceView(name = "jmx-addresses-view")
public class JMXAddressesView extends Panel implements View {

    private JMXAddressesGrid jmxAddressesGrid;

    @Inject
    public JMXAddressesView(JMXAddressesGrid jmxAddressesGrid){
        this.jmxAddressesGrid = jmxAddressesGrid;
        setContent(jmxAddressesGrid);
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
