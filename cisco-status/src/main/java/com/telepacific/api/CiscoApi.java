package com.telepacific.api;

import com.telepacific.domain.VLan;

import java.util.List;

public interface CiscoApi {
    List<String> availableDevices();
    List<VLan> getAllKnownVLans();
    List<String> availableInterfaces(String device);
}
