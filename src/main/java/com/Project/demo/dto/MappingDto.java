package com.Project.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MappingDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long roleId;
    private String userId;
    private Long FileId;
    private Long EventId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getFileId() {
        return FileId;
    }

    public void setFileId(Long fileId) {
        FileId = fileId;
    }

    public Long getEventId() {
        return EventId;
    }

    public void setEventId(Long eventId) {
        EventId = eventId;
    }

}