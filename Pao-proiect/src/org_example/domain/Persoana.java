package org_example.domain;

public class Persoana {
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected int id;
    protected String nume;

    public Persoana(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }
}