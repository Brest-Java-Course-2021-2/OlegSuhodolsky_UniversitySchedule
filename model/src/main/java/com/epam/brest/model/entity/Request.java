package com.epam.brest.model.entity;

public class Request {

    private int IDR;
    private int ID;
    private String groupe;
    private String pairs;
    private String subject;

    public Request(int IDR, int ID, String groupe, String pairs, String subject) {
        this.IDR = IDR;
        this.ID = ID;
        this.groupe = groupe;
        this.pairs = pairs;
        this.subject = subject;
    }

    public Request() {
    }

    public int getIDR() {
        return IDR;
    }

    public void setIDR(int IDR) {
        this.IDR = IDR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getPairs() {
        return pairs;
    }

    public void setPairs(String pairs) {
        this.pairs = pairs;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
