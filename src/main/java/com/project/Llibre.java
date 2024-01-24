package com.project;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany
    @JoinColumn(name = "autorId", insertable = false, updatable = false)
    private long autorId;

    @ManyToMany(mappedBy = "llibres")
    private Set<Biblioteca> biblioteques;

    @ManyToMany(mappedBy = "llibres")
    private Set<Biblioteca> persones;

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

    public long getAutorId() {
        return autorId;
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

    public List<Biblioteca> queryBibliotecas() {
        long id = this.getLlibreId();
        return Manager.queryTable("SELECT DISTINCT b.* FROM Llibre_Biblioteca lb, Biblioteca b WHERE b.id = lb.biblioteca_id AND lb.llibres_id = " + id);
    }

    @Override
    public String toString() {
        List<Biblioteca> bibliotecas = queryBibliotecas();
        String bibliotecasString = bibliotecas.stream().map(b -> b.getNom()).collect(Collectors.joining(", "));

        return this.getLlibreId() + ": " + this.getNom() + ", Editorial: " + this.getEditorial() + ", Bibliotecas relacionadas: [" + bibliotecasString + "]";
    }
}
