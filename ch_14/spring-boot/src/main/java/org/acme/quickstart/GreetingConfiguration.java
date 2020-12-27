package org.acme.quickstart;

import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "greeting") // <1>
public class GreetingConfiguration {

    @Size(min = 2) // <2>
    private String message;
    private Configuration configuration; // <3>
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message; 
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}