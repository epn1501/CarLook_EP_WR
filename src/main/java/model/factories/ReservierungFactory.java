package model.factories;

import entities.Reservierung;
import model.objects.dto.ReservierungRequest;
import model.objects.dto.User;

public class ReservierungFactory {

    public static Reservierung createReservierung (ReservierungRequest request, User user){
        Reservierung reservierung = new Reservierung();


        reservierung.setUser(user);


        return reservierung;
    }

}
