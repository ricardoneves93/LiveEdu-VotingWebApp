package com.sheepcow.voting.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Poll {
	
	@Id
	public String id;
	
	public String pollName;
	public String pollQuestion;
	public String pollOwner;
	public List<String> options;
	
	// Constructor
	public Poll(String pollName, String pollQuestion, String pollOwner, List<String> options) {
		this.pollName = pollName;
		this.pollQuestion = pollQuestion;
		this.pollOwner = pollOwner;
		this.options = options;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", pollName=" + pollName + ", pollQuestion=" + pollQuestion + ", pollOwner="
				+ pollOwner + ", options=" + options + "]";
	}
}
