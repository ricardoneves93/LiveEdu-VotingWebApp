package com.sheepcow.voting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sheepcow.voting.repositories.PollRepository;

@SpringBootApplication
public class VotingwebappLiveeduApplication/* implements CommandLineRunner*/ {
	
	@Autowired
	PollRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(VotingwebappLiveeduApplication.class, args);
	}
	
	/*
	@Override
	public void run(String... arg0) throws Exception {
		
		//repository.deleteAll();
		
		String pollOwner = "Sheep";
		String pollQuestion = "What is the color of your jeans?";
		String pollName = "jeans";
		Map<String, Integer> pollOptions = new HashMap<String, Integer>();
		pollOptions.put("Green", 5);
		pollOptions.put("Blue", 0);
		pollOptions.put("Red", 15);
		
		Poll poll = new Poll(pollName, pollQuestion, pollOwner, pollOptions);
		
		//System.out.println(repository.findPollByPollName("jeans"));
		//System.out.println(repository.findPollByPollOwner("Sheep"));
		
		//repository.save(poll);
	}
	*/
}
