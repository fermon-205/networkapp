package com.telepacific.components;

import com.google.inject.Inject;

import com.telepacific.api.Cisco;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.NativeSelect;

@UIScope
public class DeviceSelector extends NativeSelect{

    @Inject
    public DeviceSelector(Cisco cisco){
        addItems(cisco.availableDevices());
    }
}
