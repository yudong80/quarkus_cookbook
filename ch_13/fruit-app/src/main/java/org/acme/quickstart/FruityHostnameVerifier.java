package org.acme.quickstart;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class FruityHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        if ("fruityvice.com".equals(hostname)) {
            return true;
        }

        return false;
    }
    
}