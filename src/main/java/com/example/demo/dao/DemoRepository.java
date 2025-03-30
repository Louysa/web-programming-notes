package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.model.Person2;

@Repository
public class DemoRepository{

    @Autowired
	private JdbcTemplate jdbcTemplate;
    
private RowMapper<Person2> rowMapper = new RowMapper<Person2>() {

    
		public Person2 mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person2 person = new Person2();
			person.setId(rs.getLong("ID"));
			person.setFirstName(rs.getString("FNAME"));
			person.setLastName(rs.getString("LNAME"));
			person.setDateOfBirth(rs.getObject("DATEOFBIRTH", LocalDate.class));
			return person;
		}
		
	};

    public List<Person2> findAll(){
        String query = "SELECT * FROM PERSON";

        return jdbcTemplate.query(query,rowMapper);
    };

    public Person2 findById(long id){
        String query = "SELECT * FROM PERSON WHERE ID = ?";
        return DataAccessUtils.singleResult(jdbcTemplate.query(query, rowMapper, id));
    };

    public void create(Person2 person){
        String query = "INSERT INTO PERSON(FNAME,LNAME,DATEOFBIRTH) VALUES (?,?,?)";
        jdbcTemplate.update(query,person.getFirstName(),person.getLastName(),person.getDateOfBirth());
    };

    public void delete(long id){
      String query =   "DELETE FROM PERSON WHERE ID = ?";
      jdbcTemplate.update(query, id);
    };
}