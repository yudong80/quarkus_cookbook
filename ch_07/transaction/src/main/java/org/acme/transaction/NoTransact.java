package org.acme.transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.NEVER;

@ApplicationScoped
public class NoTransact {
    @Transactional(NEVER)
    public String word() {
        return "Hi";
    }
}
