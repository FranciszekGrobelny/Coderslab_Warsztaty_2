package pl.coderslab.models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Solution {
    private int id;
    private LocalDate created;
    private LocalDate updated;
    private String description;

    public Solution(){};

    public Solution(LocalDate created, LocalDate updated, String description) {
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                '}';
    }
}
