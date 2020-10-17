// tag::base[]
package org.acme.panache;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;

@Entity
public class Library extends PanacheEntity {
    public String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
               mappedBy = "library")
    public List<Inventory> inventory;
// end::base[]
    // tag::encoding[]
    public String encodedName() {
        String result;

        try {
            result = URLEncoder.encode(name, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            result = name;
        }

        return result;
    }
    // end::encoding[]
    // tag::find[]
    public static Library findByName(String name) {
        return Library
                .find("SELECT l FROM Library l " +
                      "LEFT JOIN fetch l.inventory " +
                      "WHERE l.name = :name ",
                        Parameters.with("name", name)).firstResult();
    }
    // end::find[]
// tag::base[]
}
// end::base[]
