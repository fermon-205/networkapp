package com.telepacific.cdi;

import com.google.inject.AbstractModule;

import com.tailf.cdb.Cdb;
import com.tailf.conf.Conf;
import com.tailf.conf.ConfException;

import java.io.IOException;
import java.net.Socket;

public class TelepacificModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            Socket socket = new Socket("10.196.96.155", Conf.NCS_PORT);

            Cdb cdb = new Cdb("my_cdb", socket);

            bind(Cdb.class).toInstance(cdb);
        } catch (IOException | ConfException e) {
            throw new RuntimeException(e);
        }
    }
}
