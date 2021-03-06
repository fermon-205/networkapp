package com.telepacific.cisco;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.ConfBuf;
import com.tailf.conf.ConfException;
import com.tailf.conf.ConfValue;
import com.telepacific.api.CiscoApi;
import com.telepacific.cdi.CdbProvider;
import com.telepacific.domain.JMXAddress;
import com.telepacific.domain.VLan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Singleton
public class Cisco implements CiscoApi {

    @Inject
    private CdbProvider cdbProvider;

    public Cisco() {
    }


    @Override
    public List<String> availableDevices() {

        CdbSession cdbSession = null;

        try {
            cdbSession = cdbProvider.get().startSession(CdbDBType.CDB_RUNNING);

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

    @Override
    public List<VLan> getAllKnownVLans() {
        List<VLan> vlans = new ArrayList<>();

        CdbSession cdbSession = null;

        try{
            cdbSession = cdbProvider.get().startSession(CdbDBType.CDB_RUNNING);

            int numberOfInstances = cdbSession.getNumberOfInstances("/devices/device");

            for(int i = 0; i < numberOfInstances; i++){
                int numberOfVlans = cdbSession.getNumberOfInstances("/devices/device[%d]/config/ios:interface/Vlan",i);

                for(int j = 0; j < numberOfVlans; j++){
                    ConfValue nameConfValue = cdbSession.getElem("/devices/device[%d]/config/ios:interface/Vlan[%d]/name",i,j);
                    ConfValue addressConfValue = cdbSession.getElem("/devices/device[%d]/config/ios:interface/Vlan[%d]/ip/address/primary/address",i,j);

                    final String nameString = Objects.toString(nameConfValue, "unknown");

                    final String addressString = Objects.toString(addressConfValue, "unknown");

                    VLan vLan = new VLan(nameString, addressString);

                    vlans.add(vLan);
                }
            }

            return vlans;
        } catch (IOException | ConfException e) {
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

    @Override
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
