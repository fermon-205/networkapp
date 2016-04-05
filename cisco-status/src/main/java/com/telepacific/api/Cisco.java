package com.telepacific.api;

import com.google.common.collect.ImmutableList;
import com.google.inject.Singleton;

import com.telepacific.domain.JMXAddress;

import java.util.List;
import java.util.Map;

@Singleton
public class Cisco {

    public List<String> availableDevices(){

        return ImmutableList.of("device1", "device2");
    }

    public List<String> availableInterfaces(String device){
        return ImmutableList.of("jmx-addresses");
    }

    public List<JMXAddress> getJMXAddresses(String selectedDevice) {
        return ImmutableList.of(
                new JMXAddress("physical_interface_1", "192.168.1.1"),
                new JMXAddress("physical_interface_2", "192.168.1.2")
        );
    }
}
