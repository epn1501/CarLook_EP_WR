package model.objects.dto;

public class ReservierungRequest {

    private int number;
    private Auto auto;

    public Auto getAuto(){
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }


}