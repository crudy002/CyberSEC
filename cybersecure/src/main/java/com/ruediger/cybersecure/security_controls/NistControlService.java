package com.ruediger.cybersecure.security_controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NistControlService {

    private final NistControlRepository nistControlRepository;

    @Autowired
    public NistControlService(NistControlRepository nistControlRepository) {
        this.nistControlRepository = nistControlRepository;
    }

    // Retrieve all Control entries
    public List<NistControl> getAllControls() {
        return nistControlRepository.findAll();
    }

    // Retrieve a specific Control by controlId
    public Optional<NistControl> getControlById(String controlId) {
        return nistControlRepository.findById(controlId);
    }

    // Create a new Control entry
    public NistControl createControl(NistControl nistControl) {
        return nistControlRepository.save(nistControl);
    }


    // Delete a Control entry by controlId
    public void deleteControl(String controlId) {
        nistControlRepository.deleteById(controlId);
    }
}

