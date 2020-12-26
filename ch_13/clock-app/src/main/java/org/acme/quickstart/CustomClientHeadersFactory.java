package org.acme.quickstart;

import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

public class CustomClientHeadersFactory implements ClientHeadersFactory {

  @Override
  public MultivaluedMap<String, String> update(
      MultivaluedMap<String, String> incomingHeaders, // <1>
      MultivaluedMap<String, String> clientOutgoingHeaders) { // <2>

    final MultivaluedMap<String, String> headers = 
      new MultivaluedHashMap<String, String>(incomingHeaders);
    headers.putAll(clientOutgoingHeaders); // <3>

    final List<String> auth = headers.get("x-auth"); // <4>
    headers.put("Authorization", auth);
    headers.remove("x-auth");

    return headers;
  }
}
