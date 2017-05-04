package com.sheepcow.voting.entities;

import java.util.Map;

import org.springframework.data.annotation.Id;

public class Poll {
	
	@Id
	public String id;
	
	public String pollName;
	public String pollQuestion;
	public String pollOwner;
	public Map<String, Integer> pollOptions;
	
	// Constructor
	public Poll(String pollName, String pollQuestion, String pollOwner, Map<String, Integer> pollOptions) {
		this.pollName = pollName;
		this.pollQuestion = pollQuestion;
		this.pollOwner = pollOwner;
		this.pollOptions = pollOptions;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", pollName=" + pollName + ", pollQuestion=" + pollQuestion + ", pollOwner="
				+ pollOwner + ", pollOptions=" + pollOptions + "]";
	}
}
