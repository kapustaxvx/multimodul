package com.example.application.dao;

import com.example.api.beans.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User createUser(User user){
        Timestamp timestamp = Timestamp.from(user.getBirthDate());
        final String SQL = "INSERT INTO users (name, birth_date) VALUES (:name, :birth_date)";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name", user.getName());
        namedParameters.put("birth_date", timestamp);
        int status = jdbcTemplate.update(SQL, namedParameters);
        if (status!= 0){
            System.out.println("User saved:" + user.toString());
        } else System.out.println("User save failed");
        return user;
    }
}
