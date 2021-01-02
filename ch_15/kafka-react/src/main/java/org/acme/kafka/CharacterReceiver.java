package org.acme.kafka;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

@ApplicationScoped
public class CharacterReceiver {
  @Incoming("ascii-char")
  public CompletionStage<Void> processKafkaChar(Message<String> character) {
    return CompletableFuture.runAsync(() -> {
      System.out.println("Received a message from Kafka " 
          + "using CompletableFuture: '" + character.getPayload() + "'");
    });
  }

  @Incoming("ascii-char")
  public void processCharacter(String character) {
    System.out.println("Received a String from kafka: '" + character + "'");
  }
}
