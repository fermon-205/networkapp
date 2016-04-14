package com.telepacific.cdi;

import com.google.inject.AbstractModule;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.Conf;
import com.tailf.conf.ConfBuf;
import com.tailf.conf.ConfException;
import com.tailf.conf.ConfIPv4;
import com.tailf.conf.ConfValue;

import java.io.IOException;
import java.net.Socket;

public class TelepacificModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            Socket socket = new Socket("localhost", Conf.NCS_PORT);

            Cdb cdb = new Cdb("my_cdb", socket);

            System.err.println("opening cdb session");

            CdbSession session = cdb.startSession(CdbDBType.CDB_RUNNING);

            System.err.println(session.getNumberOfInstances("/services/vlan"));

            for(int i = 0; i < session.numInstances("/servers/server"); i++) {
                ConfBuf name = (ConfBuf) session.getElem("/servers/server[%d]/hostname", i);
                ConfIPv4 ip =  (ConfIPv4) session.getElem("/servers/server[%d]/ip", i);
            }

            bind(Cdb.class).toInstance(cdb);
        } catch (IOException | ConfException e) {
            throw new RuntimeException(e);
        }
    }
}
