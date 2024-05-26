package org_example.domain;

import java.util.Date;

public class Programare {
    private int id;
    private int idClient;
    private int idMedic;
    private Date data;
    private String motiv;

    public Programare(int id, int idClient, int idMedic, Date data, String motiv) {
        this.id = id;
        this.idClient = idClient;
        this.idMedic = idMedic;
        this.data = data;
        this.motiv = motiv;
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public Date getData() {
        return data;
    }

    public String getMotiv() {
        return motiv;
    }
    @Override
    public String toString() {
        return "Programare{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idMedic=" + idMedic +
                ", data=" + data +
                ", motiv='" + motiv + '\'' +
                '}';
    }}