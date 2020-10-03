package org.acme.quickstart;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class InMemoryConfigSource implements ConfigSource {

    private Map<String, String> prop = new HashMap<>();

    public InMemoryConfigSource() { // <1>
        prop.put("greeting.color", "red");
    }

    @Override
    public int getOrdinal() { // <2>
        return 500;
    }

    @Override
    public Map<String, String> getProperties() { // <3>
        return prop;
    }

    @Override
    public String getValue(String propertyName) { // <4>
        return prop.get(propertyName);
    }

    @Override
    public String getName() { // <5>
        return "MemoryConfigSource";
    }

}