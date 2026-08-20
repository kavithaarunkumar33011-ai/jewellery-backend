package com.jewellery.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Table(name="users") @NoArgsConstructor @AllArgsConstructor
	public class User {
	    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
	    private Long id;
	    private String name;
	    @Column(unique = true) 
	    private String email;
	    @Column(unique = true)
	    private String username;
	    private String password;
	    private String role; // USER, ADMIN
	}

