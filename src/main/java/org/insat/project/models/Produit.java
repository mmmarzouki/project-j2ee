package org.insat.project.models;

import javax.persistence.*;

@Entity
@Table(name = "produits", schema = "j2eeprojet")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "libelle", table = "produits")
    private String libelle;
    @Column (name = "description", table = "produits")
    private String description;
    @Column (name = "prix", table = "produits")
    private float prix;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    public Produit() {
    }

    public Produit(String libelle, String description, float prix, Categorie categorie) {
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
    }
}
