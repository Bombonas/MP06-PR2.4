package com.project;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Llibre", 
	uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Llibre implements Serializable{
    @Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY) // L'id es genera automàticament
	private long llibreId;

    @Column(name = "nom")
	private String nom;

	@Column(name = "editorial")
	private String editorial;

    @ManyToOne
    @JoinColumn(name = "autorId")
    private Autor autor;

    @ManyToMany(mappedBy = "llibres")
    private Set<Biblioteca> biblioteques;

    @ManyToMany(mappedBy = "llibres")
    private Set<Persona> persones;

    Llibre(){}

    Llibre(String name, String editorial){
        this.nom = name;
        this.editorial = editorial;
    }

    public long getLlibreId() {
        return llibreId;
    }

    public void setLlibreId(long llibreId) {
        this.llibreId = llibreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Autor getAutorId() {
        return autor;
    }

    public Set<Biblioteca> getBiblioteques() {
        return biblioteques;
    }

    public void setBiblioteques(Set<Biblioteca> biblioteques) {
        this.biblioteques = biblioteques;
    }

    public Set<Persona> getPersones() {
        return persones;
    }

    public void setPersones(Set<Persona> persones) {
        this.persones = persones;
    }

    @Override
    public String toString() {
        return this.getLlibreId() + ": " + this.getNom() + ", Editorial: " + this.getEditorial();
    }
}
