package gui.windows;


import com.vaadin.ui.*;
import model.objects.dto.Auto;

public class ReservierungsWindow extends Window {


    public ReservierungsWindow(Auto auto) {
        super("Reservierungsliste");
        center();

        VerticalLayout content = new VerticalLayout();
        //content.addComponent(new Label("Reservierung"));
        Grid<Auto> grid = new Grid<>("Auswahl");
        grid.addColumn(Auto::getId).setCaption("ID");
        grid.addColumn(Auto::getMarke).setCaption("Marke");
        grid.addColumn(Auto::getPs).setCaption("PS");
        grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
        grid.addColumn(Auto::getDescription).setCaption("Description");
        content.addComponent(grid);
        content.setMargin(true);
        setContent(content);



        Button reserverierungsButton = new Button("OK");
        //reserverierungsButton.addClickListener(newevent -> close());
        reserverierungsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                //ReservierungRequest request = new ReservierungRequest();
                //request.setAuto(auto);

                //ReservierungProcess.getInstance().createReservierung(request, ReservierungsWindow.this);
                close();
            }
        });



        content.addComponent(reserverierungsButton);
        content.setComponentAlignment(reserverierungsButton, Alignment.MIDDLE_CENTER);
    }
}
