package com.project;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Persona", 
	uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Persona implements Serializable{
    @Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY) // L'id es genera automàticament
	private long personaId;

    @Column(name = "dni")
	private String dni;

    @Column(name = "nom")
	private String nom;

    @Column(name = "telefon")
	private int telefon;
	
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "Llibre_Persona", 
		joinColumns = {@JoinColumn(referencedColumnName = "id")}, 
		inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
	private Set<Employee> llibres;
}
