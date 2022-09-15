package bjack.venusrestblog.repository;

import bjack.venusrestblog.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}