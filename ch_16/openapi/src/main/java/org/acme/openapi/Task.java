package org.acme.openapi;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.json.bind.annotation.JsonbDateFormat;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class Task {
    public String description;

    @Schema(description = "Flag indicating the task is complete")
    public Boolean complete;

    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm")
    @Schema(example = "2019-12-25T06:30", type = SchemaType.STRING,
            implementation = LocalDateTime.class,
            pattern = "yyyy-MM-dd'T'HH:mm",
            description = "Date and time for the reminder.")
    public LocalDateTime reminder;

    public Task() {
    }

    public Task(String description,
                LocalDateTime reminder,
                Boolean complete) {
        this.description = description;
        this.reminder = reminder;
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(description, task.description) &&
                Objects.equals(reminder, task.reminder) &&
                Objects.equals(complete, task.complete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, reminder, complete);
    }
}
