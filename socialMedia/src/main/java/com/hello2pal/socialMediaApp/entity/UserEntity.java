package com.hello2pal.socialMediaApp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID",unique = true, nullable = false)
    private String userId;

    @Column(name = "NAME")
    private String usernName;

    @Column(name = "GENDER")
    private String gender;

}