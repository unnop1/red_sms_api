package com.nt.red_sms_api.service;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class KafkaClientService {

    @Value("${kafka.bootstrap.server}")
    private String bootstrapServer;

    private AdminClient client = null;

    private final Object lock = new Object();

    private Properties props = new Properties();

    @PostConstruct
    public void init() {
        // Initialize props after the value of bootstrapServer has been injected
        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty("security.protocol", "SASL_PLAINTEXT");
        props.setProperty("sasl.mechanism", "SCRAM-SHA-256");
        props.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"admin\" password=\"admin-secret\";");
        client = AdminClient.create(props);
    }

    // public KafkaClientService() {
    //     // Ideally, you would import these settings from a properties file or the like
    //     // props.setProperty("ssl.endpoint.identification.algorithm", "https");
    //     // props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "34.142.215.79:9092,34.142.251.254:9092");
    //     props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    //     props.setProperty("security.protocol", "SASL_PLAINTEXT");
    //     props.setProperty("sasl.mechanism", "SCRAM-SHA-256");
    //     props.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"admin\" password=\"admin-secret\";");
    //     client = AdminClient.create(props);
    // }
    public void teardown() {
        client.close();
    }

    public void topicListing() throws InterruptedException, ExecutionException {
        ListTopicsResult ltr = client.listTopics();
        KafkaFuture<Set<String>> names = ltr.names();
        System.out.println(names.get());
    }


    public String adminPublishMessage(String topic, String message) {
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            producer.send(record);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
