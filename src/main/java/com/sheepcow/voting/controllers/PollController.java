package com.sheepcow.voting.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sheepcow.voting.entities.Poll;
import com.sheepcow.voting.entities.PollInput;
import com.sheepcow.voting.repositories.PollRepository;

@RestController
public class PollController {
	
	@Autowired
	PollRepository pollRespository;
	
	@RequestMapping(value = "api/polls/list", method = {RequestMethod.GET})
	public @ResponseBody List<Poll> getPolls() {
		return pollRespository.findAll();
	}
	
	@RequestMapping(value = "api/polls/{pollId}", method = {RequestMethod.GET})
	public @ResponseBody Poll getPollById(@PathVariable String pollId) {
		return pollRespository.findOne(pollId);
	}
	
	@RequestMapping(value = "api/polls", method = {RequestMethod.GET})
	public @ResponseBody List<Poll> getPollByOwner(@RequestParam(value="owner", required = true) String pollOwner) {
		return pollRespository.findPollByPollOwner(pollOwner);
	}
	
	@RequestMapping(value = "api/polls", method = {RequestMethod.POST})
	public void insertPoll(@RequestBody PollInput pollInput) {
		Map<String, Integer> pollOptions = new HashMap<String, Integer>();
		for(String option : pollInput.pollOptions) {
			String randomId = UUID.randomUUID().toString();
			String concatenatedString = randomId + "/&/" + option;
			pollOptions.put(concatenatedString.trim(), 0);
		}
		
		Poll poll = new Poll(pollInput.pollName, pollInput.pollQuestion, pollInput.pollOwner, pollOptions);
		pollRespository.save(poll);
	}
	
	@RequestMapping(value = "polls/{pollId}/{optionId}/inc", method = {RequestMethod.POST})
	public void incrementVote(@PathVariable String pollId, @PathVariable String optionId) {
		System.out.println("Poll to increment vote: " + pollId + " Option to increment vote: " + optionId);
		Poll poll = pollRespository.findOne(pollId);
		if(poll != null) {
			for (Map.Entry<String, Integer> pollEntry : poll.pollOptions.entrySet())
			{	
				String originalOptionId = pollEntry.getKey().split("/&/")[0];
			    if(originalOptionId.equals(optionId)) {
			    	Integer oldValue = pollEntry.getValue();
			    	pollEntry.setValue(oldValue + 1);
			    	break;
			    }
			    
			}
			pollRespository.save(poll);
		}
	}
	
	
	@RequestMapping(value = "polls/{pollId}/{optionId}/dec", method = {RequestMethod.POST})
	public void decrementVote(@PathVariable String pollId, @PathVariable String optionId) {
		System.out.println("Poll to decrement vote: " + pollId + " Option to decrement vote: " + optionId);
		Poll poll = pollRespository.findOne(pollId);
		if(poll != null) {
			for (Map.Entry<String, Integer> pollEntry : poll.pollOptions.entrySet())
			{	
				String originalOptionId = pollEntry.getKey().split("/&/")[0];
			    if(originalOptionId.equals(optionId)) {
			    	Integer oldValue = pollEntry.getValue();
			    	if(oldValue == 0)
			    		break;
			    	pollEntry.setValue(oldValue - 1);
			    	break;
			    }
			    
			}
			pollRespository.save(poll);
		}
	}
	
	@RequestMapping(value = "api/polls/{pollId}", method = {RequestMethod.DELETE})
	public void deletePoll(@PathVariable String pollId) {
		if(pollRespository.findOne(pollId) != null) {
			pollRespository.delete(pollId);
		}
	}
	
}
