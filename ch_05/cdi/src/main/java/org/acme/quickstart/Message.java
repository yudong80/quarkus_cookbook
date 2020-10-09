package org.acme.quickstart;

public class Message {
    String quote;
    String source;

    public Message(String quote, String source) {
        this.quote = quote;
        this.source = source;
    }

    @Override
    public String toString() {
        return "\"" + quote + "\"\n-- " + source;
    }
}
