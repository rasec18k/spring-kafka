package com.codenotfound.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SpringKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaApplication.class, args);

      Properties properties = new Properties();
      properties.put("bootstrap.servers", "nlb-SAS-CRM-Busqa-0ffe95e3af3e1798.elb.us-east-1.amazonaws.com:9092");
      properties.put("connections.max.idle.ms", 10000);
      properties.put("request.timeout.ms", 5000);
      try (AdminClient client = KafkaAdminClient.create(properties))
      {
          ListTopicsResult topics = client.listTopics();
          Set<String> names = topics.names().get();
          if (names.isEmpty())
          {
              // case: if no topic found.
              System.out.println("No topic found");
          }

          System.out.println("Topic founded: " + names.toString());

      }
      catch (InterruptedException | ExecutionException e)
      {
          // Kafka is not available
          System.err.println("Kakfka is not available");
      }

      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
}
