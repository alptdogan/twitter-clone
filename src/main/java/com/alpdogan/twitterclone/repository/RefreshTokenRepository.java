package com.alpdogan.twitterclone.repository;

import com.alpdogan.twitterclone.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    RefreshToken findByUserId(int userId);

}