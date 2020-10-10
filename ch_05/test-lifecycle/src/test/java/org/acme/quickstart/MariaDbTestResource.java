package org.acme.quickstart;

import java.util.HashMap;
import java.util.Map;

import org.acme.quickstart.MariaDbTestResource.Initializer;
import org.testcontainers.containers.MariaDBContainer;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

@QuarkusTestResource(Initializer.class) // <1>
public class MariaDbTestResource {

  public static class Initializer 
      implements QuarkusTestResourceLifecycleManager { // <2>

    private MariaDBContainer mariaDBContainer; // <3>

    @Override
    public Map<String, String> start() {

      this.mariaDBContainer = new MariaDBContainer<>("mariadb:10.4.4"); // <4>
      this.mariaDBContainer.start();// <5>

      return getConfigurationParameters();
    }

    private Map<String, String> getConfigurationParameters() { // <6>
      final Map<String, String> conf = new HashMap<>();

      conf.put("quarkus.datasource.url", this.mariaDBContainer.getJdbcUrl());
      conf.put("quarkus.datsource.username", this.mariaDBContainer
                                                  .getUsername());
      conf.put("quarkus.datasource.password", this.mariaDBContainer
                                                    .getPassword());
      conf.put("quarkus.datasource.driver", this.mariaDBContainer
                                                  .getDriverClassName());

      return conf;
    }

    @Override
    public void stop() {
      if (this.mariaDBContainer != null) {
        this.mariaDBContainer.close(); // <7>
      }
    }
  }
}
