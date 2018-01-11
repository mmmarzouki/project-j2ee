package org.insat.project.models;

import javax.persistence.*;

@Entity
@Table(name = "utilisateurs", schema = "j2eeprojet")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nom", table = "utilisateurs")
    private String nom;
    @Column(name = "prenom", table = "utilisateurs")
    private String prenom;
    @Column(name = "email", table = "utilisateurs")
    private String email;
    @Column(name = "passwd", table = "utilisateurs")
    private String passwd;
    @Column(name="admin", table = "utilisateurs")
    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String email, String passwd, boolean admin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passwd = passwd;
        this.admin=admin;
    }
}
