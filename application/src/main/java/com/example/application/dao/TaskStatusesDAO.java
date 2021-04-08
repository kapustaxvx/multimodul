package com.example.application.dao;

import com.example.api.beans.TaskStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskStatusesDAO {
    private final NamedParameterJdbcTemplate jdbc;

    private final Map<TaskStatus, Integer> statusIds = new ConcurrentHashMap<>();
    private final Map<Integer, TaskStatus> statuses = new ConcurrentHashMap<>();

    public TaskStatusesDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer provideIdentifier(TaskStatus status) {
        return statusIds.computeIfAbsent(status, this::loadIdentifierFromDB);
    }

    private Integer loadIdentifierFromDB(TaskStatus status){
        final String SQL = "WITH SELECTED AS (SELECT id FROM task_statuses WHERE name = :name), " +
                "                    INSERTED AS (INSERT INTO task_statuses (name) " +
                "                        SELECT :name WHERE NOT EXISTS(SELECT 1 FROM SELECTED) RETURNING id)" +
                "                    SELECT id FROM SELECTED UNION ALL SELECT id FROM INSERTED";
        final MapSqlParameterSource params = new MapSqlParameterSource("name", status.name());

        return jdbc.queryForObject(SQL, params, Integer.class);
    }



    public TaskStatus getTaskStatus(Integer id){
        return statuses.computeIfAbsent(id, this::getTaskStatusFromDB);
    }

    private TaskStatus getTaskStatusFromDB(Integer id) {
        final String SQL = "SELECT name FROM task_statuses WHERE id = :id";
        final MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbc.queryForObject(SQL, params, TaskStatus.class);
    }
}
