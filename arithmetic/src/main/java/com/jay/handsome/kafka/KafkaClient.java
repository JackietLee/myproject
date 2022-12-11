package com.jay.handsome.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * 注释
 *
 * @author jay
 * @date 2022/7/18 16:01
 */
public class KafkaClient {

    public static boolean createTopic(String bootstrapServers, String topicName, int partitions, short replication) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        AdminClient adminClient = null;
        try {
            adminClient = KafkaAdminClient.create(properties);
            NewTopic newTopic = new NewTopic(topicName, partitions, replication);
            ArrayList<NewTopic> objects = new ArrayList<>();
            objects.add(newTopic);
            CreateTopicsResult createTopicsResult = adminClient.createTopics(objects);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if(adminClient != null){
                adminClient.close();
            }
        }
        return true;
    }

    public static Set<String> listTopic(String bootstrapServers){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        AdminClient adminClient = null;
        try {
            adminClient = KafkaAdminClient.create(properties);
            ListTopicsResult result = adminClient.listTopics();
            KafkaFuture<Set<String>> names = result.names();
            return names.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            if(adminClient != null){
                adminClient.close();
            }
        }
        return new HashSet<>();
    }

    public static void main(String[] args) {
        short i = 1;
        createTopic("192.168.0.147:30127,192.168.0.147:30128,192.168.0.147:30129", "test", 1, i);
        Set<String> strings = listTopic("192.168.0.147:30127");
        System.out.println(strings);
    }
}
