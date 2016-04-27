package com.telepacific.data;

import com.google.inject.Inject;

import com.tailf.conf.ConfException;
import com.telepacific.api.Cisco;
import com.telepacific.domain.VLan;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.guice.annotation.UIScope;

import java.io.IOException;

@UIScope
public class VLanContainer extends BeanItemContainer<VLan> {

    @Inject
    public VLanContainer(Cisco cisco) throws IllegalArgumentException, ConfException, IOException {
        super(VLan.class);

        addAll(cisco.getAllKnownVLans());
    }
}
