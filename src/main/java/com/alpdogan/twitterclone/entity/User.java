package com.alpdogan.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String username;
    String password;

/*//    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Tweet> tweets;

    //    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Comment> comments;

    //    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Like> likes;*/

}
