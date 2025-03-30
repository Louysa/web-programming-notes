package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DemoRepository;
import com.example.demo.model.Person2;

@Service
public class DemoService {
    
    @Autowired
	private DemoRepository repository;

    public List<Person2> findAll(){
        return repository.findAll();
    }
    public Person2 findById(long id){
        return repository.findById(id);
    };
    public void create(Person2 person){
        repository.create(person);
    };
    public void delete(long id){
        repository.delete(id);
    };
}
