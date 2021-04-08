package com.example.api.beans;

public class AssignRequest {

    private final Long userId;

    public AssignRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
