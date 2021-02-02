package com.example.projettest.model.entity;
import com.example.projettest.model.constants.TablesName;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = TablesName.SALARIE)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Salarie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "codesalarie", length = 10)
    private String codeSalarie;
    @Column(name = "prenom", length = 10)
    private String prenom;
    @Column(name = "fonction")
    private String fonction;
    @Column(name = "anneExp")
    private Long anneExp;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "salaire")
    private Long salaire;
    @Column(name = "date")
    private Long date;

    public Salarie() {
        super();
    }

    public Salarie(String codeSalarie, String prenom, String fonction, Long anneExp, String adresse, Long salaire, Long date) {
        this.codeSalarie = codeSalarie;
        this.prenom = prenom;
        this.fonction = fonction;
        this.anneExp = anneExp;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeSalarie() {
        return codeSalarie;
    }

    public void setCodeSalarie(String codeSalarie) {
        this.codeSalarie = codeSalarie;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Long getAnneExp() {
        return anneExp;
    }

    public void setAnneExp(Long anneExp) {
        this.anneExp = anneExp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getSalaire() {
        return salaire;
    }

    public void setSalaire(Long salaire) {
        this.salaire = salaire;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Salarie{" +
                "id=" + id +
                ", codeSalarie='" + codeSalarie + '\'' +
                ", prenom='" + prenom + '\'' +
                ", fonction='" + fonction + '\'' +
                ", anneExp=" + anneExp +
                ", adresse='" + adresse + '\'' +
                ", salaire=" + salaire +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && (o instanceof Salarie)
                && this.getId().equals(((Salarie) o).getId()))
            return true;

        return false;
    }

        @Override
    public int hashCode() {
            return super.hashCode();
    }
}
