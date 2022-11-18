package com.springblog.springblog.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
	
	
	// 如果沒標示ID會報錯
	//jpa没标识出ID导致的问题，因为部分表不存在主键，
	//所以生成的代码中对应实体类无 @ID 注解，报错信息中会打印出是哪些类，添加上 @ID 注解重新启动。
	
	@Id
	@GeneratedValue(strategy = 
		GenerationType.IDENTITY)
	private long id;
	
	
	
	private String name;
	private String email;
	private String body;
	
	/*
    FetchType.LAZY只在用到時才載入關聯的物件。
    FetchType.EAGER在查詢時立刻載入關聯的物件。
    在此沒有產生post_id但是會自動生成出來
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable =  false)
	private Post post;
	
	
	
}
