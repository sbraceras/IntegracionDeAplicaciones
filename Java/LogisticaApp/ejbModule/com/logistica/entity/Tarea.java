package com.logistica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarea {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	 Long id;
	 String description;
	 public Tarea(String description) {
	 this.description = description;
	 }


}
