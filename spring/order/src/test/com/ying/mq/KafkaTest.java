package com.ying.mq;

//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

public class KafkaTest {

    /**
     * 测试kafka消息produce
     */
    @Test
    public void testProduce() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.31.205:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

//        Producer<String, String> producer = new KafkaProducer<>(props);
//        for (int i = 0; i < 100; i++)
//            producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
//
//        producer.close();
    }
}
