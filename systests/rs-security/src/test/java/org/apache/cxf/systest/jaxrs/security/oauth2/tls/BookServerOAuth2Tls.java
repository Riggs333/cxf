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

package org.apache.cxf.systest.jaxrs.security.oauth2.tls;

import java.net.URL;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.helpers.JavaUtils;
import org.apache.cxf.testutil.common.AbstractBusTestServerBase;
import org.apache.cxf.testutil.common.TestUtil;

public class BookServerOAuth2Tls extends AbstractBusTestServerBase {
    public static final String PORT = TestUtil.getPortNumber("jaxrs-oauth2-tls");
    private static final URL SERVER_CONFIG_FILE =
        BookServerOAuth2Tls.class.getResource(JavaUtils.isFIPSEnabled()
                                              ? "serverTls-fips.xml"
                                                  : "serverTls.xml");

    protected void run() {
        SpringBusFactory bf = new SpringBusFactory();
        Bus springBus = bf.createBus(SERVER_CONFIG_FILE);
        BusFactory.setDefaultBus(springBus);
        setBus(springBus);

        try {
            new BookServerOAuth2Tls();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            BookServerOAuth2Tls s = new BookServerOAuth2Tls();
            s.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        } finally {
            System.out.println("done!");
        }
    }
}
