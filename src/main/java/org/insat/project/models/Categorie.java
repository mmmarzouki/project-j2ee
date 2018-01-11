package org.insat.project.models;

import javax.persistence.*;

@Entity
@Table(name ="categorie", schema = "j2eeprojet")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nom", table = "categorie")
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie(String nom) {
        this.nom = nom;
    }

    public Categorie() {
    }
}