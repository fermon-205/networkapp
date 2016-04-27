package com.telepacific.cdi;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.Conf;
import com.tailf.conf.ConfBuf;
import com.tailf.conf.ConfException;
import com.telepacific.api.LoginApi;
import com.telepacific.login.LoginApiImpl;

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
        bind(LoginApi.class).to(LoginApiImpl.class);

        boolean localDevelopment = parseBoolean(getProperty("local_dev"));

        if(localDevelopment){
            install(new JpaPersistModule("cisco_status_dev"));

            try {
                Cdb cdb = mock(Cdb.class);

                CdbSession cdbSession = mock(CdbSession.class);

                ConfBuf confValue = mock(ConfBuf.class);

                when(confValue.toString()).thenReturn("mock conf value");

                when(cdb.startSession(CdbDBType.CDB_RUNNING)).thenReturn(cdbSession);

                when(cdbSession.getNumberOfInstances("/devices/device")).thenReturn(3);

                when(cdbSession.getElem(anyString(),anyInt())).thenReturn(confValue);

                bind(Cdb.class).toInstance(cdb);
            } catch (ConfException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            install(new JpaPersistModule("cisco_status_prod"));

            try {
                Socket socket = new Socket("localhost", Conf.NCS_PORT);

                Cdb cdb = new Cdb("my_cdb", socket);

                bind(Cdb.class).toInstance(cdb);
            } catch (IOException | ConfException e) {
                throw new RuntimeException(e);
            }
        }

        bind(PersistServiceInitializer.class).asEagerSingleton();
    }
}
