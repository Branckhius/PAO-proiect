package org_example.domain;

public class Medic extends Persoana {
    private String specialitate;
    private String orar;

    public void setSpecialitate(String specialitate) {
        this.specialitate = specialitate;
    }

    public void setOrar(String orar) {
        this.orar = orar;
    }


    public Medic(int id, String nume, String specialitate, String orar) {
        super(id, nume);
        this.specialitate = specialitate;
        this.orar = orar;
    }

    public String getSpecialitate() {
        return specialitate;
    }

    public String getOrar() {
        return orar;
    }
    @Override
    public String toString() {
        return "Medic{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", specializare='" + specialitate + '\'' +
                ", orar='" + orar + '\'' +
                '}';
    }
}