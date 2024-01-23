/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.systest.ws.wssec11.server;

import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.helpers.JavaUtils;
import org.apache.cxf.systest.ws.common.KeystorePasswordCallback;
import org.apache.cxf.testutil.common.AbstractBusTestServerBase;
import org.apache.cxf.ws.security.SecurityConstants;

abstract class AbstractServerRestricted extends AbstractBusTestServerBase {
    String baseUrl;
    boolean streaming;

    protected AbstractServerRestricted(String baseUrl) throws Exception {
        this.baseUrl = baseUrl;
    }

    protected AbstractServerRestricted(String baseUrl, boolean streaming) throws Exception {
        this.baseUrl = baseUrl;
        this.streaming = streaming;
    }

    protected void run() {
        doPublish(baseUrl + "/APingService", JavaUtils.isFIPSEnabled()
                  ? new APingServiceFips()
                      : new APingService());
        doPublish(baseUrl + "/A-NoTimestampPingService", JavaUtils.isFIPSEnabled()
                  ? new ANoTimestampPingServiceFips()
                      : new ANoTimestampPingService());
        doPublish(baseUrl + "/ADPingService", JavaUtils.isFIPSEnabled()
                  ? new ADPingServiceFips()
                      : new ADPingService());
        doPublish(baseUrl + "/A-ESPingService", JavaUtils.isFIPSEnabled()
                  ? new AESPingServiceFips()
                      : new AESPingService());
        doPublish(baseUrl + "/AD-ESPingService", JavaUtils.isFIPSEnabled()
                  ? new ADESPingServiceFips()
                      : new ADESPingService());
        doPublish(baseUrl + "/UXPingService", JavaUtils.isFIPSEnabled()
                  ? new UXPingServiceFips()
                      : new UXPingService());
        doPublish(baseUrl + "/UX-NoTimestampPingService", JavaUtils.isFIPSEnabled()
                  ? new UXPingServiceFips()
                      : new UXNoTimestampPingService());
        doPublish(baseUrl + "/UXDPingService", JavaUtils.isFIPSEnabled()
                  ? new UXDPingServiceFips()
                      : new UXDPingService());
        doPublish(baseUrl + "/UX-SEESPingService", JavaUtils.isFIPSEnabled()
                  ? new UXSEESPingServiceFips()
                      : new UXSEESPingService());
        doPublish(baseUrl + "/UXD-SEESPingService", JavaUtils.isFIPSEnabled()
                  ? new UXDSEESPingServiceFips()
                      : new UXDSEESPingService());
        doPublish(baseUrl + "/XPingService", JavaUtils.isFIPSEnabled()
                  ? new XPingServiceFips()
                      : new XPingService());
        doPublish(baseUrl + "/X-NoTimestampPingService", JavaUtils.isFIPSEnabled()
                  ? new XNoTimestampPingServiceFips()
                      : new XNoTimestampPingService());
        doPublish(baseUrl + "/XDPingService", JavaUtils.isFIPSEnabled()
                  ? new XDPingServiceFips()
                      : new XDPingService());
        doPublish(baseUrl + "/XD-ESPingService", JavaUtils.isFIPSEnabled()
                  ? new XDESPingServiceFips()
                      : new XDESPingService());
        doPublish(baseUrl + "/XD-SEESPingService", JavaUtils.isFIPSEnabled()
                  ? new XDSEESPingServiceFips()
                      : new XDSEESPingService());
        
    }
    private void doPublish(String url, Object obj) {
        Endpoint ep = Endpoint.create(obj);
        ep.getProperties().put(SecurityConstants.CALLBACK_HANDLER, new KeystorePasswordCallback());
        ep.getProperties().put(SecurityConstants.ENCRYPT_PROPERTIES, "restricted/bob.properties");
        if (streaming) {
            ep.getProperties().put(SecurityConstants.ENABLE_STREAMING_SECURITY, "true");
        }
        ep.publish(url);
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "A_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class APingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "A-NoTimestamp_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class ANoTimestampPingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "AD_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class ADPingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "A-ES_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class AESPingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "AD-ES_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class ADESPingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "UX_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class UXPingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "UX-NoTimestamp_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class UXNoTimestampPingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "UXD_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class UXDPingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "UX-SEES_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class UXSEESPingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "UXD-SEES_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class UXDSEESPingService extends PingService {
    }



    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "X_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XPingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "X-NoTimestamp_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XNoTimestampPingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "XD_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XDPingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "XD-ES_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XDESPingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "XD-SEES_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XDSEESPingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "X-AES128_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XAES128PingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "X-AES256_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XAES256PingService extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
                serviceName = "PingService11",
                portName = "X-TripleDES_IPingService",
                endpointInterface = "wssec.wssec11.IPingService",
                wsdlLocation =
                              "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted.wsdl")
    public static class XTripleDESPingService extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "A_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class APingServiceFips extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "A-NoTimestamp_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class ANoTimestampPingServiceFips extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "AD_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class ADPingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "A-ES_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class AESPingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "AD-ES_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class ADESPingServiceFips extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "UX_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class UXPingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "UX-NoTimestamp_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class UXNoTimestampPingServiceFips extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "UXD_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class UXDPingServiceFips extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "UX-SEES_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class UXSEESPingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "UXD-SEES_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class UXDSEESPingServiceFips extends PingService {
    }



    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "X_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XPingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "X-NoTimestamp_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XNoTimestampPingServiceFips extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "XD_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XDPingServiceFips extends PingService {
    }

    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "XD-ES_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XDESPingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "XD-SEES_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XDSEESPingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "X-AES128_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XAES128PingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "X-AES256_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XAES256PingServiceFips extends PingService {
    }
    @WebService(targetNamespace = "http://WSSec/wssec11",
        serviceName = "PingService11",
        portName = "X-TripleDES_IPingService",
        endpointInterface = "wssec.wssec11.IPingService",
        wsdlLocation = "target/test-classes/wsdl_systest_wssec/wssec11/WsSecurity11_restricted-fips.wsdl")
    public static class XTripleDESPingServiceFips extends PingService {
    }

}
