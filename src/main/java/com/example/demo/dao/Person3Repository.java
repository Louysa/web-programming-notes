package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class Person3Repository {
    

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person3> findAll(){
        return entityManager.createQuery("from Person3",Person3.class).getResultList();
    }

    public Person3 findById(long id){
        return entityManager.find(Person3.class, id);
    }

    public List<Person3> findByLastName(String lastName){
        return entityManager.createQuery("from Person3 where lastName = :last",Person3.class)
                            .setParameter("last", lastName).getResultList();
    }

    public Person3 update(Person3 person3){
        return entityManager.merge(person3);
    }
    
    public void create(Person3 person3){
        entityManager.persist(person3);
    }
    public void delete(long id){
        entityManager.remove(entityManager.getReference(Person3.class, id));
    }
}
