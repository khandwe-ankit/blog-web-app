package com.ankit.blog.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "user_name", nullable = false, length = 100)
	private String name;

	@Column(name = "user_email", nullable = false, unique = true)
	private String email;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "about")
	private String about;

}
