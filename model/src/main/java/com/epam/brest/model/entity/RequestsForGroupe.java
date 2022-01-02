package com.epam.brest.model.entity;

public class RequestsForGroupe {

    private String groupe;
    private String lector;
    private String subject;
    private Integer pairs;

    public RequestsForGroupe() {
    }

    public RequestsForGroupe(String lector, String groupe, String subject, Integer pairs) {
        this.groupe = groupe;
        this.lector = lector;
        this.subject = subject;
        this.pairs = pairs;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getPairs() {
        return pairs;
    }

    public void setPairs(Integer pairs) {
        this.pairs = pairs;
    }

    @Override
    public String toString() {
        return (this.lector + " " + this.groupe + " " + this.subject + " " + this.pairs);
    }

//    public static final Comparator<RequestsForGroupe> COMPARE_BY_PAIRS
//            = (RequestsForGroupe lhs, RequestsForGroupe rhs) -> lhs.pairs - rhs.pairs;
//            Collections.sort(al,Collections.reverseOrder(RequestsForGroupe.COMPARE_BY_PAIRS));
//            // Collections.sort(al,RequestsForGroupe.COMPARE_BY_PAIRS);
}
