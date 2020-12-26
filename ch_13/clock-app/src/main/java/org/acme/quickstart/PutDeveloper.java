package org.acme.quickstart;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;

public class PutDeveloper {

    @HeaderParam("Authorization") // <1>
    private String authorization;

    @PathParam("developerId") // <2>
    private String developerId;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

}