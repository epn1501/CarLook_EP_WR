package gui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import gui.windows.ConfirmationWindow;
import model.dao.AutoDAO;
import model.objects.dto.Auto;
import services.util.Views;

public class HinzufuegenView extends VerticalLayout implements View {

    public void setUp(){

        final TextField markeEingabe = new TextField();
        markeEingabe.setCaption("Marke: ");
        markeEingabe.setWidth("475px");
        markeEingabe.setPlaceholder("Marke");

        final TextField psEingabe = new TextField();
        psEingabe.setCaption("PS: ");
        psEingabe.setWidth("475px");
        psEingabe.setPlaceholder("PS");

        final TextField baujahrEingabe = new TextField();
        baujahrEingabe.setCaption("Baujahr: ");
        baujahrEingabe.setWidth("475px");
        baujahrEingabe.setPlaceholder("Baujahr");

        final TextField descriptionEingabe = new TextField();
        descriptionEingabe.setCaption("Description: ");
        descriptionEingabe.setWidth("475px");
        descriptionEingabe.setPlaceholder("Description");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(markeEingabe);
        layout.addComponent(psEingabe);
        layout.addComponent(baujahrEingabe);
        layout.addComponent(descriptionEingabe);

        Label label = new Label( "&nbsp;", ContentMode.HTML); //Platzhalter

        Button hinzufuegen = new Button("Hinzufügen", VaadinIcons.PLUS_SQUARE_LEFT_O);
        hinzufuegen.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                String marke = markeEingabe.getValue();
                String ps = psEingabe.getValue();
                String baujahr = baujahrEingabe.getValue();
                String description = descriptionEingabe.getValue();

                int psZahl = Integer.parseInt(ps);
                int baujahrZahl = Integer.parseInt(baujahr);

                Auto neuAuto = new Auto();
                neuAuto.setMarke(marke);
                neuAuto.setPs(psZahl);
                neuAuto.setBaujahr(baujahrZahl);
                neuAuto.setDescription(description);

                AutoDAO.getInstance().createAuto(neuAuto);

                System.out.println("Marke: " + marke + " PS: " + psZahl + "Baujahr:  " + neuAuto.getBaujahr() + "Description:  " + neuAuto.getDescription() );
                ConfirmationWindow window = new ConfirmationWindow("Neues Auto wurde hinzugefügt!");
                UI.getCurrent().addWindow(window);
                UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
            }
        });

        Button buttonZurueck = new Button("Zurück", VaadinIcons.ARROW_LEFT);
        buttonZurueck.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.MAIN));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(buttonZurueck, hinzufuegen);

        layout.addComponent(label);
        layout.addComponent(horizontalLayout);
        layout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

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
