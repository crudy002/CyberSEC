package com.ruediger.cybersecure.security_controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nist-controls")
public class NistControlController {

    private final NistControlService nistControlService;

    @Autowired
    public NistControlController(NistControlService nistControlService) {
        this.nistControlService = nistControlService;
    }

    // Get all Control entries
    @GetMapping
    public ResponseEntity<List<NistControl>> getAllControlFamilies() {
        List<NistControl> nistControls = nistControlService.getAllControls();
        return new ResponseEntity<>(nistControls, HttpStatus.OK);
    }
}
