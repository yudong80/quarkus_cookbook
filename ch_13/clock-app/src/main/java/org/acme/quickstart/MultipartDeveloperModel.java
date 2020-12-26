package org.acme.quickstart;

import java.io.InputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class MultipartDeveloperModel {

    @FormParam("avatar") // <1>
    @PartType(MediaType.APPLICATION_OCTET_STREAM) // <2>
    public InputStream file;

    @FormParam("name")
    @PartType(MediaType.TEXT_PLAIN)
    public String developerName;
    
}