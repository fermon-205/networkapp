package com.telepacific.api;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.ConfBuf;
import com.tailf.conf.ConfException;
import com.telepacific.domain.JMXAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class Cisco {

    @Inject
    private Cdb cdb;

    public Cisco() {
    }

    public List<String> availableDevices() {
        try {
            final CdbSession cdbSession = cdb.startSession(CdbDBType.CDB_RUNNING);

            final int numberOfInstances = cdbSession.getNumberOfInstances("/servers/server");

            List<String> availableDevices = new ArrayList<>(numberOfInstances);

            for(int i = 0; i < numberOfInstances; i++){
                ConfBuf name = (ConfBuf) cdbSession.getElem("/servers/server[%d]/hostname", i);

                availableDevices.add(name.toString());
            }

            return availableDevices;
        } catch (ConfException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> availableInterfaces(String device) {
        return ImmutableList.of("jmx-addresses");
    }

    public List<JMXAddress> getJMXAddresses(String selectedDevice) {
        return ImmutableList.of(
                new JMXAddress("physical_interface_1", "192.168.1.1"),
                new JMXAddress("physical_interface_2", "192.168.1.2")
        );
    }
}
