package entities;

import model.objects.dto.Auto;
import model.objects.dto.User;

public class Reservierung {

    private int reservierungsID;
    private User user;
    private Auto auto;


    public int getReservierungsID() {

        return reservierungsID;
    }

    public void setReservierungsID(int reservierungsID) {

        this.reservierungsID = reservierungsID;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }




    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}

