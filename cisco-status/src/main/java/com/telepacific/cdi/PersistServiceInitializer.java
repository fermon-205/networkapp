package com.telepacific.cdi;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.PersistService;

import javax.persistence.EntityManager;

public class PersistServiceInitializer {
    @Inject
    PersistServiceInitializer(PersistService service) {
        service.start();
    }
}
