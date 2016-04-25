package com.telepacific.cdi;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.Conf;
import com.tailf.conf.ConfBuf;
import com.tailf.conf.ConfException;
import com.tailf.conf.ConfIPv4;
import com.tailf.conf.ConfValue;
import com.telepacific.api.LoginApi;
import com.telepacific.login.LoginApiImpl;

import java.io.IOException;
import java.net.Socket;

public class TelepacificModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LoginApi.class).to(LoginApiImpl.class);

        install(new JpaPersistModule("cisco_status"));
        bind(PersistServiceInitializer.class).asEagerSingleton();

        try {
            Socket socket = new Socket("localhost", Conf.NCS_PORT);

            Cdb cdb = new Cdb("my_cdb", socket);

            bind(Cdb.class).toInstance(cdb);
        } catch (IOException | ConfException e) {
            throw new RuntimeException(e);
        }
    }
}
