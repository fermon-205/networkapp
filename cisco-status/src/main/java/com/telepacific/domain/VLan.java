package com.telepacific.domain;

public class VLan {

    private String name;
    private String ipAddress;

    public VLan(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
