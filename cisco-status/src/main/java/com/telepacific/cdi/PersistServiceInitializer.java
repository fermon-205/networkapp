package com.telepacific.cdi;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.PersistService;

import com.telepacific.domain.User;

import javax.persistence.EntityManager;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;

public class PersistServiceInitializer {
    @Inject
    PersistServiceInitializer(PersistService service, Provider<EntityManager> entityManagerProvider) {
        service.start();

        boolean localDevelopment = parseBoolean(getProperty("local_dev"));

        if (localDevelopment) {
            User user = new User("user1", "secret");

            entityManagerProvider.get().getTransaction().begin();

            entityManagerProvider.get().persist(user);

            entityManagerProvider.get().getTransaction().commit();
        }
    }
}
