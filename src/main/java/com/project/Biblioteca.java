package com.project;

import java.io.Serializable;
import java.util.Set;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "Biblioteca", 
	uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Biblioteca implements Serializable{
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bibliotecaId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "ciutat")
    private String ciutat;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Llibre_Biblioteca",
        joinColumns = {@JoinColumn(referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Biblioteca> llibres;

    public Biblioteca() { }

    public long getBibliotecaId() {
        return bibliotecaId;
    }

    public void setBibliotecaId(long bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Set<Biblioteca> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Biblioteca> llibres) {
        this.llibres = llibres;
    }

    public List<Object[]> queryBibliotecas() {
        long id = this.getBibliotecaId();
        return Manager.queryTable("SELECT DISTINCT b.* FROM Llibre_Biblioteca lb, Biblioteca b WHERE b.id = lb.biblioteca_id AND lb.llibres_id = " + id);
    }

    @Override
    public String toString() {
        String str = Manager.tableToString(queryBibliotecas()).
        replaceAll("\n", " | ");

        return this.getBibliotecaId() + ": " + this.getNom() + ", " + this.getCiutat() + ", Llibres relacionados: [" + str + "]";
    }
}
