package com.telepacific.api;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.ConfBuf;
import com.tailf.conf.ConfException;
import com.tailf.conf.ConfValue;
import com.telepacific.domain.JMXAddress;
import com.telepacific.domain.VLan;

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

        CdbSession cdbSession = null;

        try {
            cdbSession = cdb.startSession(CdbDBType.CDB_RUNNING);

            final int numberOfInstances = cdbSession.getNumberOfInstances("/devices/device");

            List<String> availableDevices = new ArrayList<>(numberOfInstances);

            for(int i = 0; i < numberOfInstances; i++){
                ConfBuf name = (ConfBuf) cdbSession.getElem("/devices/device[%d]/name", i);

                availableDevices.add(name.toString());
            }

            cdbSession.endSession();

            return availableDevices;
        } catch (ConfException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(cdbSession != null){
                try {
                    cdbSession.endSession();
                } catch (ConfException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<VLan> getAllKnownVLans() throws ConfException, IOException {
        List<VLan> vlans = new ArrayList<>();

        CdbSession cdbSession = null;

        try{
            cdbSession = cdb.startSession(CdbDBType.CDB_RUNNING);

            int numberOfInstances = cdbSession.getNumberOfInstances("/devices/device");

            for(int i = 0; i < numberOfInstances; i++){
                int numberOfVlans = cdbSession.getNumberOfInstances("/devices/device[%d]/config/ios:interface/Vlan",i);

                for(int j = 0; j < numberOfVlans; j++){
                    ConfValue name =cdbSession.getElem("/devices/device[%d]/config/ios:interface/Vlan[%d]/name",i,j);
                    ConfValue address = cdbSession.getElem("/devices/device[%d]/config/ios:interface/Vlan[%d]/ip/address/primary/address",i,j);

                    VLan vLan = new VLan(name.toString(), address.toString());

                    vlans.add(vLan);
                }
            }

            return vlans;
        } finally {
            if(cdbSession != null){
                cdbSession.endSession();
            }
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
