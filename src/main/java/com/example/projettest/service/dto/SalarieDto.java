package com.example.projettest.service.dto;

import com.example.projettest.repository.SalarieRepository;

import java.util.Date;

public class SalarieDto {

    private Long id;
    private String CodeSalarie;

    private String prenom;

    private String fonction;

    private Long anneexp;

    private String adresse;

    private Long salaire;

    private Long date;

    public SalarieDto(Long id, String codeSalarie, String prenom, String fonction, Long anneExp, String adresse, Long salaire, Long date) {
        this.id = id;
        CodeSalarie = codeSalarie;
        this.prenom = prenom;
        this.fonction = fonction;
        this.anneexp = anneExp;
        this.adresse = adresse;
        this.salaire = salaire;
        this.date = date;
    }

    public SalarieDto() {
        super();
    }

    public String getCodeSalarie() {
        return CodeSalarie;
    }

    public void setCodeSalarie(String codeSalarie) {
        CodeSalarie = codeSalarie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return anneexp;
    }

    public void setAnneExp(Long anneExp) {
        this.anneexp = anneExp;
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

}
