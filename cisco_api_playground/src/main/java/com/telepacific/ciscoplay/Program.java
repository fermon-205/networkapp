package com.telepacific.ciscoplay;

import com.tailf.cdb.Cdb;
import com.tailf.cdb.CdbDBType;
import com.tailf.cdb.CdbSession;
import com.tailf.conf.*;
import java.io.IOException;
import java.net.Socket;

public class Program {
    public static void main(String[] args) throws IOException, ConfException {
        Socket socket = new Socket("localhost", Conf.NCS_PORT);

        Cdb cdb = new Cdb("my_cdb", socket);

        final CdbSession cdbSession = cdb.startSession(CdbDBType.CDB_RUNNING);
	System.err.println("number of devices: " + numberOfInstances);

	for(int i = 0; i < numberOfInstances; i++){
		ConfBuf name = (ConfBuf)cdbSession.getElem("/devices/device[%d]/name", i);
		System.err.println("dev " + i  + " name is " + name);
	
		//ConfBuf iface = (ConfBuf)cdbSession.getElem("/devices/device[%d]/iface", i);
        
        	//System.err.println("iface " + i + " is " + iface);
        
       		//ConfInt32 unit = (ConfInt32)cdbSession.getElem("/devices/device[%d]/unit", i);

       		//System.err.println("unit " + i + " is " + unit);
        
        	ConfUInt16 vid = (ConfUInt16)cdbSession.getElem("/devices/device[%d]/vid", i);

        	System.err.println("vid " + i + " is " + vid);
	}
    }
}
