package model.objects.dto;

import model.dao.RoleDAO;

import java.util.List;

public class User {

    private String vorname = null;
    private String nachname = null;
    private String login = null;
    private String passwort = null;
    private List<Role> roles = null;
    private String role = null;

    public String getVorname() {

        return vorname;
    }

    public void setVorname(String vorname) {

        this.vorname = vorname;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public boolean hasRole(String role) {
        //Lazy Load
        if (this.roles == null) {
            getRoles();
        }

        for (Role r : roles) {
            if (r.getBezeichnung().equals(role)) {
                return true;
            }
        }
        return false;
    }

    /*
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    */



    private void getRoles() {
        this.roles = RoleDAO.getInstance().getRolesForUser(this);

    }


}
