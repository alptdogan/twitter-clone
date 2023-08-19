package com.alpdogan.twitterclone.repository;

import com.alpdogan.twitterclone.entity.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Integer> {
}
