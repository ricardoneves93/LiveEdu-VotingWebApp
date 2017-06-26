package com.sheepcow.voting.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sheepcow.voting.entities.Poll;
import com.sheepcow.voting.entities.PollInput;
import com.sheepcow.voting.entities.PollOption;
import com.sheepcow.voting.entities.PollOutput;
import com.sheepcow.voting.models.VoteModel;
import com.sheepcow.voting.repositories.PollOptionRepository;
import com.sheepcow.voting.repositories.PollRepository;

@Controller
public class PollController {
	
	@Autowired
	PollRepository pollRepository;
	
	@Autowired
	PollOptionRepository pollOptionRepository;
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "polls/list", method = {RequestMethod.GET})
	public @ResponseBody List<PollOutput> getPolls() {
		List<Poll> polls = pollRepository.findAll();
		List<PollOutput> outputPolls = new ArrayList<>();
		for(Poll poll: polls) {
			List<String> pollOptionsIds = poll.options;
			List<PollOption> pollOptions = new ArrayList<>();
			for(String pollOptionId: pollOptionsIds) {
				PollOption pollOption = pollOptionRepository.findOne(pollOptionId);
				pollOptions.add(pollOption);
			}
			outputPolls.add(new PollOutput(poll.id, poll.pollName, poll.pollQuestion, poll.pollOwner, pollOptions));
		}
		
		return outputPolls;
	}
	
	@RequestMapping(value = "polls/{pollId}", method = {RequestMethod.GET})
	public String getPollById(@PathVariable String pollId, Model model) {
		Poll poll = pollRepository.findOne(pollId);
		if(poll != null) {
			List<String> pollOptionsIds = poll.options;
			List<PollOption> pollOptions = new ArrayList<>();
			for(String pollOptionId: pollOptionsIds) {
				PollOption pollOption = pollOptionRepository.findOne(pollOptionId);
				pollOptions.add(pollOption);
			}
			PollOutput pollOutput = new PollOutput(poll.id, poll.pollName, poll.pollQuestion, poll.pollOwner, pollOptions);
			model.addAttribute("poll", pollOutput); 
		}
		
		return "poll";
	}
	
	/*
	@RequestMapping(value = "api/polls", method = {RequestMethod.GET})
	public @ResponseBody List<PollOutput> getPollByOwner(@RequestParam(value="owner", required = true) String pollOwner) {
		List<Poll> polls = pollRespository.findPollByPollOwner(pollOwner);
		List<PollOutput> outputPolls = new ArrayList<>();
		for(Poll poll: polls) {
			List<String> pollOptionsIds = poll.pollOptions;
			List<PollOption> pollOptions = new ArrayList<>();
			for(String pollOptionId: pollOptionsIds) {
				PollOption pollOption = pollOptionRepository.findOne(pollOptionId);
				pollOptions.add(pollOption);
			}
			outputPolls.add(new PollOutput(poll.id, poll.pollName, poll.pollQuestion, poll.pollOwner, pollOptions));
		}
		
		return outputPolls;
	}
	*/
	
	@RequestMapping(value = "api/polls", method = {RequestMethod.POST})
	public void insertPoll(@RequestBody PollInput pollInput) {
		List<String> pollOptions = new ArrayList<>();
		for(String option : pollInput.pollOptions) {
			String id = pollOptionRepository.save(new PollOption(option)).id;
			pollOptions.add(id);
		}
		
		Poll poll = new Poll(pollInput.pollName, pollInput.pollQuestion, pollInput.pollOwner, pollOptions);
		pollRepository.save(poll);
	}
	
	@RequestMapping(value = "polls/{pollId}/{requestOptionId}/inc", method = {RequestMethod.POST})
	public String incrementVote(@PathVariable String pollId, @PathVariable String requestOptionId) {
		System.out.println("Poll to increment vote: " + pollId + " Option to increment vote: " + requestOptionId);
		Poll poll = pollRepository.findOne(pollId);
		if(poll != null) {
			List<String> pollOptions = poll.options;
			for(String databaseOptionId : pollOptions) {
				if(requestOptionId.equals(databaseOptionId)) {
					PollOption pollOption = pollOptionRepository.findOne(requestOptionId);
					if(pollOption != null) {
						Integer oldVotes = pollOption.optionVotes;
						pollOption.optionVotes = oldVotes + 1;
						pollOptionRepository.save(pollOption);
				    	break;
					}
					
				}
			}
		}
		
		return "inc";
	}
	
	@RequestMapping(value = "polls/dec", method = {RequestMethod.POST})
	public void decrementVote(@ModelAttribute VoteModel voteModel) {
		System.out.println("Poll to decrement vote: " + voteModel.getPollId() + " Option to decrement vote: " + voteModel.getRequestOptionId());
		Poll poll = pollRepository.findOne(voteModel.getPollId());
		if(poll != null) {
			List<String> pollOptions = poll.options;
			for(String databaseOptionId : pollOptions) {
				if(voteModel.getRequestOptionId().equals(databaseOptionId)) {
					PollOption pollOption = pollOptionRepository.findOne(voteModel.getRequestOptionId());
					if(pollOption != null) {
						Integer oldVotes = pollOption.optionVotes;
						if(oldVotes == 0)
							break;
						pollOption.optionVotes = oldVotes - 1;
						pollOptionRepository.save(pollOption);
				    	break;
					}
					
				}
			}
		}
	}
	
	@RequestMapping(value = "api/polls/{pollId}", method = {RequestMethod.DELETE})
	public void deletePoll(@PathVariable String pollId) {
		Poll poll = pollRepository.findOne(pollId);
		if(poll != null) {
			pollRepository.delete(pollId);
			List<String> pollOptionsIds = poll.options;
			for(String pollOptionId: pollOptionsIds) {
				pollOptionRepository.delete(pollOptionId);
			}
		}
	}
	
}
