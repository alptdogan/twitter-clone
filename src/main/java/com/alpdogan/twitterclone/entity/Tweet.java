package com.alpdogan.twitterclone.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Lob
    @Column(columnDefinition = "text")
    String text;

}
