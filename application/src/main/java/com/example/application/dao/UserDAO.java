package com.example.application.dao;

import com.example.api.beans.User;
import com.example.api.beans.UserBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private final NamedParameterJdbcTemplate jdbc;

    public UserDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User createUser(User user) {
        final Timestamp timestamp = Timestamp.from(user.getBirthDate());
        final String SQL = "INSERT INTO users (name, birth_date) VALUES (:name, :birth_date) " +
                "RETURNING user_id , name , birth_date";
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName());
        params.addValue("birth_date", timestamp);

        final User savedUser = jdbc.queryForObject(SQL, params, new UserMapper());
        logger.info("User saved:" + savedUser);
        return savedUser;
    }

    public User getUserById(Long userId) {
        final String SQL = "SELECT user_id, name, birth_date FROM users WHERE user_id = :user_id";
        final MapSqlParameterSource params = new MapSqlParameterSource("user_id", userId);
        try {
            final User getUser = jdbc.queryForObject(SQL, params, new UserMapper());
            logger.info("[{}] Find user: {}", userId, getUser);
            return getUser;
        } catch (EmptyResultDataAccessException e) {
            logger.info("[{}] User not found", userId);
            return null;
        }
    }


    public User updateUser(Long userId, User user) {
        final String SQL = "UPDATE users " +
                "SET name = :name, birth_date = :birth_date " +
                "WHERE user_id = :user_id " +
                "RETURNING user_id, name, birth_date";
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("name", user.getName());
        params.addValue("birth_date", Timestamp.from(user.getBirthDate()));
        try {
            final User updateUser = jdbc.queryForObject(SQL, params, new UserMapper());
            logger.info("[{}] User updates to: {}", userId, updateUser);
            return updateUser;
        } catch (EmptyResultDataAccessException e) {
            logger.info("[{}] User not found", userId);
            return null;
        }
    }

    public List<User> getAllUsers() {
        final String SQL = "SELECT user_id, name, birth_date FROM users";
        return jdbc.query(SQL, new UserMapper());
    }


    static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return UserBuilder.create().withUserId(resultSet.getLong("user_id"))
                    .withName(resultSet.getString("name"))
                    .withBirthDate(resultSet.getTimestamp("birth_date").toInstant())
                    .build();
        }
    }
}

