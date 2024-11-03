package com.ruediger.cybersecure.security_controls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NistControlRepository extends JpaRepository<NistControl, String> {
    // Additional query methods (if needed) can be defined here
}

