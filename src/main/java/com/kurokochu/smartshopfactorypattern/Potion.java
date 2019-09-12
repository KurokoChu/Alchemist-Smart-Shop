package com.kurokochu.smartshopfactorypattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Potion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	void use() {
		
	};

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
