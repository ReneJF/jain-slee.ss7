/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/*
 * The Java Call Control API for CAMEL 2
 *
 * The source code contained in this file is in in the public domain.
 * It can be used in any project or product without prior permission,
 * license or royalty payments. There is  NO WARRANTY OF ANY KIND,
 * EXPRESS, IMPLIED OR STATUTORY, INCLUDING, WITHOUT LIMITATION,
 * THE IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 * AND DATA ACCURACY.  We do not warrant or make any representations
 * regarding the use of the software or the  results thereof, including
 * but not limited to the correctness, accuracy, reliability or
 * usefulness of the software.
 */

package org.mobicents.jcc.inap.protocol;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.mobicents.jcc.inap.protocol.parms.LegID;
import org.mobicents.jcc.inap.protocol.parms.RequestedInformationList;
import org.mobicents.jcc.inap.protocol.tcap.Util;
import org.mobicents.util.Utils;

/**
 *
 * @author Oleg Kulikov
 */
public class CallInformationReport extends Operation {
    
    private RequestedInformationList list;
    private LegID legID;
    
    /** Creates a new instance of CallInformationReport */
    public CallInformationReport() {
    }

    
    public CallInformationReport(byte[] bin) throws IOException {  
        ByteArrayInputStream in = new ByteArrayInputStream(bin);
        
        //in.read();        //call information args
        //Util.readTag(in);
        
        while (in.available() > 0) {
            int param = in.read() & 0xff;
            int length = Util.readLen(in);
            
            byte[] buff = new byte[length];
            in.read(buff);
            
            switch (param) {
                case 0xA0 :
                    list = new RequestedInformationList(buff);
                    break;
                case 0xA3 :
                    legID = new LegID(buff);
                    break;
            }
        }
    }
    
    public RequestedInformationList getReqInfo() {
        return list;
    }
    
    public LegID getLegID() {
        return legID;
    }
    
    public byte[] toByteArray() {
        return null;
    }
    
    public String toString() {
        return "CallInformationReport[" + list + ", " + legID + "]";
    }
}