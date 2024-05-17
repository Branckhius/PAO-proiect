package org_example.domain;

public class Client extends Persoana {
    private String adresa;
    private String email;
    private String telefon;

    public Client(int id, String nume, String adresa, String email, String telefon) {
        super(id, nume);
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
    }
    void setAdresa(String Adresa){
        this.adresa=Adresa;
    }
    void setEmail(String Email){
        this.email=Email;
    }
    void setTelefon(String Telefon){
        this.telefon=Telefon;
    }
    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}