package com.example.crudjpa.model.services;

import com.example.crudjpa.model.entities.Pessoa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PessoaDAO {

    // CREATE
    public void save(Pessoa person) {
        Transaction transaction = null;
        try (Session session = HibernateTransaction.getSession()) {
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // READ - Buscar por ID
    public Pessoa getById(Long id) {
        try (Session session = HibernateTransaction.getSession()) {
            return session.get(Pessoa.class, id);
        }
    }

    // READ - Buscar Todos
    public List<Pessoa> getAll() {
        try (Session session = HibernateTransaction.getSession()) {
            return session.createQuery("from Pessoa", Pessoa.class).list();
        }
    }

    // UPDATE
    public void update(Pessoa person) {
        Transaction transaction = null;
        try (Session session = HibernateTransaction.getSession()) {
            transaction = session.beginTransaction();
            session.update(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateTransaction.getSession()) {
            transaction = session.beginTransaction();
            Pessoa person = session.get(Pessoa.class, id);
            if (person != null) {
                session.delete(person);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}