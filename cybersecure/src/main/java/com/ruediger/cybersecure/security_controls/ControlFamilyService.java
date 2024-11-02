package com.ruediger.cybersecure.security_controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControlFamilyService {

    private final ControlFamilyRepository controlFamilyRepository;

    @Autowired
    public ControlFamilyService(ControlFamilyRepository controlFamilyRepository) {
        this.controlFamilyRepository = controlFamilyRepository;
    }

    // Retrieve all ControlFamily entries
    public List<ControlFamily> getAllControlFamilies() {
        return controlFamilyRepository.findAll();
    }

    // Retrieve a specific ControlFamily by familyId
    public Optional<ControlFamily> getControlFamilyById(String familyId) {
        return controlFamilyRepository.findById(familyId);
    }

    // Create a new ControlFamily entry
    public ControlFamily createControlFamily(ControlFamily controlFamily) {
        return controlFamilyRepository.save(controlFamily);
    }

    // Update an existing ControlFamily entry
    public ControlFamily updateControlFamily(String familyId, ControlFamily updatedControlFamily) {
        if (controlFamilyRepository.existsById(familyId)) {
            updatedControlFamily.setFamilyId(familyId);  // Ensure the ID is set
            return controlFamilyRepository.save(updatedControlFamily);
        } else {
            throw new RuntimeException("ControlFamily with ID " + familyId + " not found.");
        }
    }

    // Delete a ControlFamily entry by familyId
    public void deleteControlFamily(String familyId) {
        controlFamilyRepository.deleteById(familyId);
    }
}

