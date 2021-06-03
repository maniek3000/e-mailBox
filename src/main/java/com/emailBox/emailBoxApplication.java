package com.emailBox;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class emailBoxApplication {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        UserRepositoryImpl userRepository = new UserRepositoryImpl(sessionFactory);
        UserService userService = new UserService(userRepository);
        UserControler userControler = new UserControler(userService);
        TestUserInterface testUserInterface = new TestUserInterface(userControler);
        testUserInterface.run();
    }
}
