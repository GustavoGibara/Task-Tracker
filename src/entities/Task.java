package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import enums.Status;

public class Task {
    private Long id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
    
    public Task(Long id, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ID - " + id  + "\n" 
                + "Tarefa: " + description + "\n" 
                + "Status : " + status + "\n" 
                + "Criado em: " + createdAt.format(dtf) + "\n"
                + "Atualizado em: " + updatedAt.format(dtf);
    }

    public String toJson() {
        String taskJson = "{\n" 
                            + "\t\"id\": " + id + ",\n"
                            + "\t\"description\": \"" + description + "\",\n"
                            + "\t\"status\": \"" + status.name() + "\",\n"
                            + "\t\"createdAt\": \"" + createdAt + "\",\n"
                            + "\t\"updatedAt\": \"" + updatedAt + "\"\n"
                            + "}";

        return taskJson;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
