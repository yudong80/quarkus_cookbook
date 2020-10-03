package org.acme.quickstart;

import javax.annotation.Priority;

import org.eclipse.microprofile.config.spi.Converter;

@Priority(300) // <1>
public class PercentageConverter implements Converter<Percentage> { // <2>

    @Override
    public Percentage convert(String value) {
        
        String numeric = value.substring(0, value.length() - 1);
        return new Percentage (Double.parseDouble(numeric) / 100);
       
    }

}