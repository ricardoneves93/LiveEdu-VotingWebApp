package com.sheepcow.voting.entities;

import java.util.List;

public class PollOutput {
	public String id;
	
	public String pollName;
	public String pollQuestion;
	public String pollOwner;
	public List<PollOption> pollOptions;
	
	public PollOutput(String id, String pollName, String pollQuestion, String pollOwner, List<PollOption> pollOptions) {
		this.id = id;
		this.pollName = pollName;
		this.pollQuestion = pollQuestion;
		this.pollOwner = pollOwner;
		this.pollOptions = pollOptions;
	}
}
