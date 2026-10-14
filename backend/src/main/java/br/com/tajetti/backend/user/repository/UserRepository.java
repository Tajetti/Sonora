package br.com.tajetti.backend.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tajetti.backend.user.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
