package com.sheepcow.voting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sheepcow.voting.entities.Poll;
import com.sheepcow.voting.repositories.PollRepository;

@RestController
public class PollController {
	
	@Autowired
	PollRepository pollRespository;
	
	@RequestMapping(value = "/polls/list", method = {RequestMethod.GET})
	public @ResponseBody List<Poll> getPolls() {
		return pollRespository.findAll();
	}
	
	@RequestMapping(value = "/polls/{pollId}", method = {RequestMethod.GET})
	public @ResponseBody Poll getPollById(@PathVariable String pollId) {
		return pollRespository.findOne(pollId);
	}
	
	@RequestMapping(value = "/polls", method = {RequestMethod.GET})
	public @ResponseBody List<Poll> getPollByOwner(@RequestParam(value="owner", required = true) String pollOwner) {
		return pollRespository.findPollByPollOwner(pollOwner);
	}
		
}
