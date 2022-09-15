package bjack.venusrestblog.repository;

import bjack.venusrestblog.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {

}
