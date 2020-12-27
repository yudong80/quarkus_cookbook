package org.acme.quickstart;

import org.springframework.stereotype.Service;

@Service // <1>
public class PrefixService {

    public String appendPrefix(String message) {
        return "- " + message;
    }

}