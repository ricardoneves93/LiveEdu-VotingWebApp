package com.sheepcow.voting;


import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.sheepcow.voting.repositories.PollRepository;

@SpringBootApplication
public class VotingwebappLiveeduApplication {
	
	@Autowired
	PollRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(VotingwebappLiveeduApplication.class, args);
	}
	
	/*
	@Bean
    public Jongo jongo() {
        DB db = new MongoClient("127.0.0.1", 27017).getDB("test");
        return new Jongo(db);
    }

    @Bean
    public MongoCollection users() {
        return jongo().getCollection("users");
    }
    */
    
}
