package com.galvanize;

public class ToDo {
        private int id;
    private String description;

    private Boolean complete;

    public ToDo(int id, String description) {
        this.id = id;
        this.description = description;
        this.complete = false;
    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

}
