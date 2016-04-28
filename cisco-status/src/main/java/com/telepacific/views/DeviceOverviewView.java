package com.telepacific.views;

import com.google.inject.Inject;

import com.telepacific.api.CiscoApi;
import com.telepacific.domain.VLan;
import com.vaadin.guice.annotation.GuiceView;

import java.util.Collection;

@GuiceView(name = "")
public class DeviceOverviewView extends GridViewBase<VLan> {

    @Inject
    private CiscoApi cisco;

    public DeviceOverviewView() {
        super(VLan.class, "all available VLans");
        grid.setColumnOrder("name", "ipAddress");
    }

    @Override
    protected Collection<VLan> getBeans() {
        return cisco.getAllKnownVLans();
    }
}
