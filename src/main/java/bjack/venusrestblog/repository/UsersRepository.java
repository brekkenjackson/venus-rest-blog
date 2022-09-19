package bjack.venusrestblog.repository;

import bjack.venusrestblog.data.User;
import bjack.venusrestblog.dto.UserFetchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByEmail(String email);

//        @Query(nativeQuery = true)
//    List<UserFetchDTO> fetchUserDTOs();
}