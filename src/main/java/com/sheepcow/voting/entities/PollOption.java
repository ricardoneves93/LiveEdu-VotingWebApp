package com.sheepcow.voting.entities;

import org.springframework.data.annotation.Id;

public class PollOption {
	@Id
	public String id;
	
	public String optionName;
	public Integer optionVotes;
	
	public PollOption() {}
	
	public PollOption(String optionName) {
		this.optionName = optionName;
		this.optionVotes = 0;
	}
	
	public PollOption(String optionName, Integer optionVotes) {
		this.optionName = optionName;
		this.optionVotes = optionVotes;
	}
	
}
