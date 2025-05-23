package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Person3Repository;
import com.example.demo.model.Person3;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Person3Service {
    
    @Autowired
    private Person3Repository person3Repository;

    public List<Person3> findAll(){
        return person3Repository.findAll();
    }


    public Person3 findById(long id){
        return person3Repository.findById(id);
    }

    public List<Person3> findByLastName(String lastName){
        return person3Repository.findByLastName(lastName);
    }

    public void create(Person3 person3){
        person3Repository.create(person3);
    }
    public void delete(long id ){
        person3Repository.delete(id);
    }
}
