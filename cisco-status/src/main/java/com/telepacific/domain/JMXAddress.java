package com.telepacific.domain;

public class JMXAddress {
    private String physicalInterface;
    private String ipAddress;

    public JMXAddress(String physicalInterface, String ipAddress) {
        this.physicalInterface = physicalInterface;
        this.ipAddress = ipAddress;
    }

    public String getPhysicalInterface() {
        return physicalInterface;
    }

    public void setPhysicalInterface(String physicalInterface) {
        this.physicalInterface = physicalInterface;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
