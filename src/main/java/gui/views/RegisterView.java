package gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import services.util.Views;

public class RegisterView extends VerticalLayout implements View {
    public static final String CLASSNAME ="REGISTERSEITE";


    public void setUp(){

        this.setSizeFull();

        final TextField userVorname = new TextField();
        userVorname.setCaption("Vorname: ");
        userVorname.setWidth("475px");

        final TextField userNachname = new TextField();
        userNachname.setCaption("Nachname: ");
        userNachname.setWidth("475px");

        final TextField userRegister = new TextField();
        userRegister.setCaption("E-Mail: ");
        userRegister.setWidth("475px");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");
        passwordField.setWidth("475px");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(userVorname);
        layout.addComponent(userNachname);
        layout.addComponent(userRegister);
        layout.addComponent(passwordField);

        Label label = new Label( "&nbsp;", ContentMode.HTML); //Platzhalter

        Button buttonRegister = new Button("Weiter", FontAwesome.ARROW_RIGHT);

        buttonRegister.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                //ToDo-Bedingung für MAIN, FELDER müssen korrekt ausgefüllt sein
                //erst dann zu Main weiterleiten

                UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

            }
        });


        //layout.addComponent(buttonRegister);
        //layout.setComponentAlignment(buttonRegister, Alignment.BOTTOM_RIGHT);

        // buttonRegister.setPrimaryStyleName(CLASSNAME + "-register");
        // layout.addComponent(buttonRegister);
        Button buttonZurueck = new Button("Zurück", FontAwesome.ARROW_LEFT);
        buttonZurueck.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.LOGIN));
        layout.addComponents(buttonZurueck, buttonRegister);
        // layout.setComponentAlignment(buttonZurueck, Alignment.BOTTOM_LEFT);
        // layout.setComponentAlignment(buttonRegister, Alignment.BOTTOM_RIGHT);
        //layout.addComponents(buttonZurueck, buttonRegister);
        layout.setComponentAlignment(buttonZurueck, Alignment.BOTTOM_LEFT);

        Panel panel = new Panel("Jetzt registrieren - gratis in nur 2 Minuten!");
        panel.addStyleName("register");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);
        //panel.setSizeUndefined();

        panel.setWidth("500px");
        panel.setHeight("500px");


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){

        this.setUp();

    }


}
