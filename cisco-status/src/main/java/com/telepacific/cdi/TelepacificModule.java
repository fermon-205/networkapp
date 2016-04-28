package com.telepacific.cdi;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.Conf;
import com.tailf.conf.ConfBuf;
import com.tailf.conf.ConfException;
import com.telepacific.api.CiscoApi;
import com.telepacific.api.LoginApi;
import com.telepacific.cisco.Cisco;
import com.telepacific.domain.VLan;
import com.telepacific.login.Login;

import org.mockito.Mockito;

import java.io.IOException;
import java.net.Socket;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TelepacificModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LoginApi.class).to(Login.class);

        boolean localDevelopment = parseBoolean(getProperty("local_dev"));

        if(localDevelopment){
            install(new JpaPersistModule("cisco_status_dev"));

            CiscoApi ciscoApi = mock(CiscoApi.class);

            when(ciscoApi.availableDevices()).thenReturn(ImmutableList.of("device1", "device2", "device3"));
            when(ciscoApi.availableInterfaces(anyString())).thenReturn(ImmutableList.of("interface1", "interface2", "interface3"));
            when(ciscoApi.getAllKnownVLans()).thenReturn(ImmutableList.of(
                    new VLan("vlan1", "123.456.789.000"),
                    new VLan("vlan2", "123.456.789.001"),
                    new VLan("vlan3", "123.456.789.002"))
            );

            bind(CiscoApi.class).toInstance(ciscoApi);
        } else {
            install(new JpaPersistModule("cisco_status_prod"));

            try {
                Socket socket = new Socket("localhost", Conf.NCS_PORT);

                Cdb cdb = new Cdb("my_cdb", socket);

                bind(Cdb.class).toInstance(cdb);
            } catch (IOException | ConfException e) {
                throw new RuntimeException(e);
            }

            bind(CiscoApi.class).to(Cisco.class);
        }

        bind(PersistServiceInitializer.class).asEagerSingleton();
    }
}
