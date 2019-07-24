package com.hello2pal.socialMediaApp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String userId;

    @Column(name = "NAME")
    private String usernName;

    @Column(name = "GENDER")
    private String gender;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_RELATIONS",
            joinColumns = @JoinColumn(name = "FOLLOWED_ID"),
            inverseJoinColumns = @JoinColumn(name = "FOLLOWER_ID"))
    @EqualsAndHashCode.Exclude
    private Set<UserEntity> followers;
    @ManyToMany(mappedBy = "followers")
    @EqualsAndHashCode.Exclude
    private Set<UserEntity> following;

    public UserEntity(String userId, String username, String gender) {
        this.userId = userId;
        this.usernName = username;
        this.gender = gender;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public void addFollower(UserEntity follower) {
        followers.add(follower);
        follower.following.add(this);
    }

    public void removeFollower(UserEntity follower) {
        followers.remove(follower);
        follower.following.remove(this);
    }

}