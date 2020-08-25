package model.objects.dto;

public class Auto implements  java.io.Serializable {

    private Integer id;
    private String marke;
    private Integer ps;
    private Integer baujahr;
    private String description;

    public Auto(){

    }

    public Auto ( Integer id, String marke, Integer ps, Integer baujahr, String description){
        this.id = id;
        this.marke = marke;
        this.ps = ps;
        this.baujahr = baujahr;
        this.description = description;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public Integer getPs(){
        return ps;
    }

    public void setPs(Integer ps){
        this.ps = ps;
    }
    public Integer getBaujahr(){
        return baujahr;
    }

    public void setBaujahr(Integer baujahr){
        this.baujahr = baujahr;
    }


    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }


}