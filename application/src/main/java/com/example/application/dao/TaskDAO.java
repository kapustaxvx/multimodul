package com.example.application.dao;

import com.example.api.beans.Task;
import com.example.api.beans.TaskBuilder;
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
public class TaskDAO {

    private static final Logger logger = LoggerFactory.getLogger(TaskDAO.class);
    private final NamedParameterJdbcTemplate jdbc;
    private final TaskStatusesDAO taskStatusesDAO;

    public TaskDAO(NamedParameterJdbcTemplate jdbc, TaskStatusesDAO taskStatusesDAO) {
        this.jdbc = jdbc;
        this.taskStatusesDAO = taskStatusesDAO;
    }

    public Task createTask(Task task) {
        final Timestamp creationTime = Timestamp.from(task.getCreationDate());
        final Timestamp expirationTime = Timestamp.from(task.getExpirationDate());

        final String SQL = "INSERT INTO tasks (status_id, creation_date, title, expiration_date)" +
                "VALUES (:status, :creation_date, :title, :expiration_date) " +
                "RETURNING task_id, status_id, creation_date, title, expiration_date";

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("status", taskStatusesDAO.provideIdentifier(task.getStatus()));
        params.addValue("creation_date", creationTime);
        params.addValue("title", task.getTitle());
        params.addValue("expiration_date", expirationTime);

        final Task savedTask = jdbc.queryForObject(SQL, params, new TaskMapper());
        logger.info("Saved task: " + savedTask);
        return savedTask;
    }


    public void assignTask(Long taskId, Long userId) {
        final String SQL = "INSERT INTO user_tasks (user_id, task_id) VALUES (:user_id, :task_id)" +
                " ON CONFLICT DO NOTHING ";
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("task_id", taskId);
        int status = jdbc.update(SQL, params);
        if (status != 0) {
            logger.info("[{}] User assign [{}] task", userId, taskId);
        } else logger.info("Error to  assign [{}] task to [{}]", taskId, userId);
    }


    public Task getTaskById(Long taskId) {
        final String SQL = "SELECT task_id, status_id, creation_date, title, expiration_date" +
                " FROM tasks WHERE task_id = :task_id";
        final MapSqlParameterSource params = new MapSqlParameterSource("task_id", taskId);
        try {
            final Task getTask = jdbc.queryForObject(SQL, params, new TaskMapper());
            logger.info("[{}] Find task: {}", taskId, getTask);
            return getTask;
        } catch (EmptyResultDataAccessException e) {
            logger.info("[{}] Task not found", taskId);
            return null;
        }
    }

    public Task updateTask(Long taskId, Task task) {
        final String SQL = "UPDATE tasks " +
                "SET status_id = :status, title = :title, expiration_date = :expiration_date " +
                "WHERE :task_id = task_id " +
                "RETURNING task_id, status_id, creation_date, title, expiration_date";
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("task_id", taskId);
        params.addValue("status", taskStatusesDAO.provideIdentifier(task.getStatus()));
        params.addValue("title", task.getTitle());
        params.addValue("expiration_date", Timestamp.from(task.getExpirationDate()));
        try {
            final Task updateTask = jdbc.queryForObject(SQL, params, new TaskMapper());
            return updateTask;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Task> getAllTasks() {
        final String SQL = "SELECT task_id, status_id, creation_date, title, expiration_date FROM tasks";
        return jdbc.query(SQL, new TaskMapper());
    }

    public List<Task> getAllTasksOfUser(Long userId) {
        final String SQL = "SELECT task_id, status_id, creation_date, title, expiration_date " +
                "FROM tasks WHERE task_id IN (SELECT task_id FROM user_tasks WHERE user_id = :user_id)";
        final MapSqlParameterSource params = new MapSqlParameterSource("user_id", userId);
        return jdbc.query(SQL, params, new TaskMapper());
    }

    class TaskMapper implements RowMapper<Task> {
        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException {
            return TaskBuilder.create()
                    .withTaskId(resultSet.getLong("task_id"))
                    .withStatus(taskStatusesDAO.getTaskStatus(resultSet.getInt("status_id")))
                    .withCreationDate(resultSet.getTimestamp("creation_date").toInstant())
                    .withTitle(resultSet.getString("title"))
                    .withExpirationDate(resultSet.getTimestamp("expiration_date").toInstant())
                    .build();
        }
    }
}
