package br.com.tajetti.backend.user;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.tajetti.backend.user.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table (name = "users")
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull 
    @Column(unique = true)
    private String username;

    @NotNull 
    @Column (unique = true)
    private String email;

    @NotNull 
    @Column (name = "password_hash")
    private String password;

    @Column (name = "display_name")
    private String displayName;

    @Enumerated (EnumType.STRING)
    private UserRole role = UserRole.USER;

    @CreationTimestamp 
    @Column (name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
    
    @UpdateTimestamp 
    @Column (name = "updated_at")
    private OffsetDateTime updatedAt;
}