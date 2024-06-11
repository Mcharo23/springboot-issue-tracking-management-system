package net.assignment.itms.user.repository;

import net.assignment.itms.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    Optional<User> findByEmail(@NonNull String email);

    @NonNull
    Optional<User> findById(@NonNull Long userId);
}
