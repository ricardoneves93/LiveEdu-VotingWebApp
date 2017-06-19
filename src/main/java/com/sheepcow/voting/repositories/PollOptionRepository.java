package com.sheepcow.voting.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sheepcow.voting.entities.PollOption;

public interface PollOptionRepository extends MongoRepository<PollOption, String> {

}
