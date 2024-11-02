package com.ruediger.cybersecure.security_controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/control-families")
public class ControlFamilyController {

    private final ControlFamilyService controlFamilyService;

    @Autowired
    public ControlFamilyController(ControlFamilyService controlFamilyService) {
        this.controlFamilyService = controlFamilyService;
    }

    // Get all ControlFamily entries
    @GetMapping
    public ResponseEntity<List<ControlFamily>> getAllControlFamilies() {
        List<ControlFamily> controlFamilies = controlFamilyService.getAllControlFamilies();
        return new ResponseEntity<>(controlFamilies, HttpStatus.OK);
    }

    // Get a specific ControlFamily by familyId
    @GetMapping("/{familyId}")
    public ResponseEntity<ControlFamily> getControlFamilyById(@PathVariable String familyId) {
        return controlFamilyService.getControlFamilyById(familyId)
            .map(controlFamily -> new ResponseEntity<>(controlFamily, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new ControlFamily entry
    @PostMapping
    public ResponseEntity<ControlFamily> createControlFamily(@RequestBody ControlFamily controlFamily) {
        ControlFamily createdControlFamily = controlFamilyService.createControlFamily(controlFamily);
        return new ResponseEntity<>(createdControlFamily, HttpStatus.CREATED);
    }

    // Update an existing ControlFamily entry
    @PutMapping("/{familyId}")
    public ResponseEntity<ControlFamily> updateControlFamily(@PathVariable String familyId, @RequestBody ControlFamily updatedControlFamily) {
        try {
            ControlFamily controlFamily = controlFamilyService.updateControlFamily(familyId, updatedControlFamily);
            return new ResponseEntity<>(controlFamily, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a ControlFamily entry by familyId
    @DeleteMapping("/{familyId}")
    public ResponseEntity<Void> deleteControlFamily(@PathVariable String familyId) {
        controlFamilyService.deleteControlFamily(familyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
