package com.alpdogan.twitterclone.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Lob
    @Column(columnDefinition = "text")
    String text;

/*    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })*/
    @ManyToOne
    @JoinColumn(name = "user")
    User user;

/*    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })*/
    @ManyToOne
    @JoinColumn(name = "tweet")
    Tweet tweet;

}
