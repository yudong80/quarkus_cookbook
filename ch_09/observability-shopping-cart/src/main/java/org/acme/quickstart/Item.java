package org.acme.quickstart;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Item extends PanacheEntity {

    @NotNull
    public String itemId;
    public int quantity;
    public float unitPrice;

}
