package com.hello2pal.socialMediaApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "POST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long postId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Lob
    @Column(name = "CONTENT")
    private String content;

}