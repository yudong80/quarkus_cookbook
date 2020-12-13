package org.acme.quickstart.cr;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.client.CustomResourceList;

@JsonDeserialize
public class HelloResourceList extends CustomResourceList<HelloResource> { // <1>
}