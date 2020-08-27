package gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import services.util.Views;

public class HizufuegenView extends VerticalLayout implements View {


    public void setUp(){

        final TextField marke = new TextField();
        marke.setCaption("Marke: ");
        marke.setWidth("475px");
        marke.setPlaceholder("Marke");


        final TextField ps = new TextField();
        ps.setCaption("PS: ");
        ps.setWidth("475px");
        ps.setPlaceholder("PS");


        final TextField baujahr = new TextField();
        baujahr.setCaption("Baujahr: ");
        baujahr.setWidth("475px");
        baujahr.setPlaceholder("Baujahr");

        final TextField description = new TextField();
        description.setCaption("Description: ");
        description.setWidth("475px");
        description.setPlaceholder("Description");


        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(marke);
        layout.addComponent(ps);
        layout.addComponent(baujahr);
        layout.addComponent(description);

        Label label = new Label( "&nbsp;", ContentMode.HTML); //Platzhalter

        Button hinzufuegen = new Button("Hinzufügen", FontAwesome.PLUS_SQUARE_O);


        Button buttonZurueck = new Button("Zurück", FontAwesome.ARROW_LEFT);
        buttonZurueck.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.MAIN));
        layout.addComponent(buttonZurueck);


        Panel panel = new Panel("Neue Autos hinzufügen");
        panel.setStyleName("addView");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);
        panel.setWidth("500px");
        panel.setHeight("500px");



    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){


        this.setUp();
    }

}
