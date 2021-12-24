package com.epam.brest.model.entity;

import java.io.Serializable;

public class Groupe implements Serializable {

    private int idGroupe;
    private String nameGroupe;

    public Groupe(int idGroupe, String nameGroupe) {
        this.idGroupe = idGroupe;
        this.nameGroupe = nameGroupe;
    }

    public Groupe(String nameGroupe) {
        this.nameGroupe = nameGroupe;
    }

    public Groupe() {
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNameGroupe() {
        return nameGroupe;
    }

    public void setNameGroupe(String nameGroupe) {
        this.nameGroupe = nameGroupe;
    }
}
