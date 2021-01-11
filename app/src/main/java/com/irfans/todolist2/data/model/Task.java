package com.irfans.todolist2.data.model;

import com.irfans.todolist2.base.BaseModel;

public class Task extends BaseModel {
    private int id;
    private String title;
    private String description;
    private String deadline;
    private boolean privacy;
    private boolean checked;

    public Task(int id, String title, String description, String deadline, boolean privacy, boolean checked) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.privacy = privacy;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }
}
