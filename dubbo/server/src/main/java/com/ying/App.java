package com.ying;

import com.ying.dubbo.EmbeddedZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main(String[] args) {

        // start embedded zookeeper server
        new EmbeddedZooKeeper(2181, false).start();


        SpringApplication.run(App.class, args);
    }

}
