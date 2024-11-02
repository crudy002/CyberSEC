package com.ruediger.cybersecure.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("landing")
public class LandingPage extends MainLayout {

    public LandingPage() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        // Create a title
        Div title = new Div();
        title.add("Welcome to CyberSECURE");
        title.addClassName("title");

        // Create a description
        Div description = new Div();
        description.add("Your go-to solution for managing control families and compliance documentation.");
        description.addClassName("description"); // Use a class for styling

        // Create a button to navigate to Control Families
        Button navigateButton = new Button("View Control Families");
        navigateButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("control-families")); // navigate to the control families view
        });

        // Add components to the layout
        add(title, description, navigateButton);
        getStyle().set("background-color", "#f7f7f7"); // Set a light background color
        setJustifyContentMode(JustifyContentMode.CENTER); // Center content vertically
    }
}
