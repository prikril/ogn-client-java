/**
 * Copyright (c) 2014 OGN, All Rights Reserved.
 */

package org.ogn.client.demo;

import static java.lang.System.out;

import org.ogn.client.OgnBeaconListener;
import org.ogn.client.OgnClient;
import org.ogn.client.OgnClientFactory;
import org.ogn.client.OgnClientProperties;
import org.ogn.commons.beacon.AircraftBeacon;
import org.ogn.commons.utils.JsonUtils;

/**
 * A small demo program demonstrating the basic usage of the ogn-client.
 * 
 * @author wbuczak
 */
public class OgnDemoAircraftBeaconsClient {

    static {
        // ignore parsing receiver beacons, we are not interested in them in this demo and there is 
        // no point in wasting CPU on that
        System.setProperty(OgnClientProperties.PROP_OGN_CLIENT_IGNORE_RECEIVER_BEACONS, "true");
    }

    static class AircraftBeaconListener implements OgnBeaconListener<AircraftBeacon> {
        AircraftBeacon last = null;

        @Override
        public void onUpdate(AircraftBeacon beacon) {
            out.println(JsonUtils.toJson(beacon));
        }
    }

    public static void main(String[] args) throws Exception {
        OgnClient client = OgnClientFactory.createClient();

        System.out.println("connecting...");
        // client.connect("r/+51.537/+5.472/250");

        client.connect();

        client.subscribeToAircraftBeacons(new AircraftBeaconListener());

        Thread.sleep(Long.MAX_VALUE);
    }

}