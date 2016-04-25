package com.telepacific.ciscoplay;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.Conf;
import com.tailf.conf.ConfException;

import java.io.IOException;
import java.net.Socket;

public class Program {
    public static void main(String[] args) throws IOException, ConfException {
        Socket socket = new Socket("localhost", Conf.NCS_PORT);

        Cdb cdb = new Cdb("my_cdb", socket);

        final CdbSession cdbSession = cdb.startSession(CdbDBType.CDB_RUNNING);

        final int numberOfInstances = cdbSession.getNumberOfInstances("/servers/server");
    }
}
