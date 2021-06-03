package com.emailBox;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{

    private SessionFactory sessionFactory;
    private Session session;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User createUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        try{
            User user = session.createQuery("SELECT u FROM User u WHERE u.username= :username", User.class)
                    .setParameter(1, username)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
