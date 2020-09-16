package gui.windows;

import com.vaadin.ui.*;

public class ConfirmationWindow extends Window {

    public ConfirmationWindow(String text){
        super("Confirmation");
        center();

        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        setContent(content);

        Button reservierungsButton = new Button("Ok");
        reservierungsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });

        content.addComponent(reservierungsButton);
        content.setComponentAlignment(reservierungsButton, Alignment.MIDDLE_CENTER);
    }
}