package com.example.application.dao;

import com.example.api.beans.TaskStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskStatusesDAO {
    private final NamedParameterJdbcTemplate jdbc;

    public TaskStatusesDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Integer provideIdentifier(TaskStatus status) {
        final String SQL = "WITH SELECTED AS (SELECT id FROM task_statuses WHERE name = :name), " +
                "                    INSERTED AS (INSERT INTO task_statuses (name) " +
                "                        SELECT :name WHERE NOT EXISTS(SELECT 1 FROM SELECTED) RETURNING id)" +
                "                    SELECT id FROM SELECTED UNION ALL SELECT id FROM INSERTED";
        final MapSqlParameterSource params = new MapSqlParameterSource("name", status.name());

        return jdbc.queryForObject(SQL, params, Integer.class);
    }

    public TaskStatus getTaskStatus(Integer id) {
        final String SQL = "SELECT name FROM task_statuses WHERE id = :id";
        final MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbc.queryForObject(SQL, params, TaskStatus.class);
    }
}
