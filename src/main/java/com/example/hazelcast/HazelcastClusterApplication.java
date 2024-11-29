package com.example.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HazelcastClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastClusterApplication.class, args);
	}

}
