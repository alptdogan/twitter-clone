package com.alpdogan.twitterclone.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Lob
    @Column(columnDefinition = "text")
    String text;

    @ManyToOne
    @JoinColumn(name = "user")
    User user;

    @ManyToOne
    @JoinColumn(name = "tweet")
    Tweet tweet;
}
