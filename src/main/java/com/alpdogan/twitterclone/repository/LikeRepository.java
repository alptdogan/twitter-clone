package com.alpdogan.twitterclone.repository;

import com.alpdogan.twitterclone.entity.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like, Integer> {
}
