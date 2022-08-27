package com.ankit.blog.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "post_title", nullable = false)
	private String title;
	
	@Column(name = "post_content", nullable = false, length = 1000)
	private String content;
	
	private String imageName;
	
	@Column(name = "post_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDateTime;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
}
