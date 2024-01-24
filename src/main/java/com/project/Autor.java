package com.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Autor", 
	uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Autor implements Serializable{
    
    @Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long autorId;

    @Column(name = "nom")
	private String nom;

    @OneToMany
    @JoinColumn(name = "autorId") 
    private Set<Llibre> items;
}
