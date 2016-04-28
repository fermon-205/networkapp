package com.telepacific.components;

import com.google.inject.Inject;

import com.telepacific.api.CiscoApi;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.NativeSelect;

@UIScope
public class DeviceSelector extends NativeSelect {

    @Inject
    public DeviceSelector(CiscoApi cisco) {
        addItems(cisco.availableDevices());
        setNullSelectionAllowed(true);
        setNullSelectionItemId("Select Device here..");
    }
}
