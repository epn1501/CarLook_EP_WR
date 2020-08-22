package model.objects.dto;

public class NeueListeAuto extends Auto {

    private Integer id;
    private String marke;
    private Integer ps;
    private Integer baujahr;
    private String description;


    public NeueListeAuto(){

    }

    public NeueListeAuto(Integer id){
        this.id = id;
    }

    public NeueListeAuto(Integer id, String marke,  Integer ps, Integer baujahr, String description){
        this.id = id;
        this.marke = marke;
        this.ps = ps;
        this.baujahr = baujahr;
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getMarke() {
        return marke;
    }

    @Override
    public void setMarke(String marke) {
        this.marke = marke;
    }

    @Override
    public Integer getPs() {
        return ps;
    }

    @Override
    public void setPs(Integer ps) {
        this.ps = ps;
    }

    @Override
    public Integer getBaujahr() {
        return baujahr;
    }

    @Override
    public void setBaujahr(Integer baujahr) {
        this.baujahr = baujahr;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }



}
