package com.alpdogan.twitterclone.repository;

import com.alpdogan.twitterclone.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
