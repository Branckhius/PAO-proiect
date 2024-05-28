package org_example.domain;

public class Asistent extends Persoana {
    private String sectie;
    private String schimb;

    public void setSectie(String sectie) {
        this.sectie = sectie;
    }

    public void setSchimb(String schimb) {
        this.schimb = schimb;
    }

    public Asistent(int id, String nume, String sectie, String schimb) {
        super(id, nume);
        this.sectie = sectie;
        this.schimb = schimb;
    }

    public String getSectie() {
        return sectie;
    }

    public String getSchimb() {
        return schimb;
    }

    @Override
    public String toString() {
        return "Asistent{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", sectie='" + sectie + '\'' +
                ", schimb='" + schimb + '\'' +
                '}';
    }
}
