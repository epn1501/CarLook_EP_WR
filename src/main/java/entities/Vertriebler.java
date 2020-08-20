package entities;

public class Vertriebler extends Benutzer {

    private String vorname;
    private String nachname;
    private int vertrieblerID;

    @Override
    public String getNachname() {
        return nachname;
    }

    @Override
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public String getVorname() {
        return vorname;
    }


    public int getVertrieblerID() {
        return vertrieblerID;
    }


    public void setVertrieblerID(int vertrieblerID) {
        this.vertrieblerID = vertrieblerID;
    }

}
