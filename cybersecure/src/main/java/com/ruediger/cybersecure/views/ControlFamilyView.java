package com.ruediger.cybersecure.views;

import com.ruediger.cybersecure.security_controls.ControlFamily;
import com.ruediger.cybersecure.security_controls.ControlFamilyService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("control-families")
public class ControlFamilyView extends MainLayout {

    private final ControlFamilyService controlFamilyService;
    private Grid<ControlFamily> grid = new Grid<>(ControlFamily.class);

    @Autowired
    public ControlFamilyView(ControlFamilyService controlFamilyService) {
        this.controlFamilyService = controlFamilyService;
        setSizeFull();
        configureGrid();
        add(grid);
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("familyId", "name"); // Set the columns to display
        grid.setSizeFull();
    }

    private void updateGrid() {
        List<ControlFamily> controlFamilies = controlFamilyService.getAllControlFamilies();
        grid.setItems(controlFamilies);
    }
}

