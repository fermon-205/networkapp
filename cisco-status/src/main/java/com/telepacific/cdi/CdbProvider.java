package com.telepacific.cdi;

import com.google.inject.Provider;

import com.tailf.cdb.Cdb;
import com.tailf.conf.Conf;
import com.tailf.conf.ConfException;

import java.io.IOException;
import java.net.Socket;

public class CdbProvider implements Provider<Cdb> {

    private Cdb current;
    private Socket socket;

    @Override
    public Cdb get() {
        if(socket == null || socket.isClosed()){
            try {
                socket = new Socket("localhost", Conf.NCS_PORT);
                current = new Cdb("my_cdb", socket);
            } catch (IOException | ConfException e) {
                throw new RuntimeException(e);
            }
        }

        return current;
    }
}
