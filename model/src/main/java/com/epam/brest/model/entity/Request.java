package com.epam.brest.model.entity;

import java.util.Date;

public class Request {

    private int idR;
    private int id;
    private String groupe;
    private String pairs;
    private String subject;
    private Date date;

    public Request(int idR, int id, String groupe, String pairs, String subject, Date date) {
        this.idR = idR;
        this.id = id;
        this.groupe = groupe;
        this.pairs = pairs;
        this.subject = subject;
        this.date = date;
    }

    public Request(int id, String groupe, String pairs, String subject, Date date) {
        this.id = id;
        this.groupe = groupe;
        this.pairs = pairs;
        this.subject = subject;
        this.date = date;
    }

    public Request() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
