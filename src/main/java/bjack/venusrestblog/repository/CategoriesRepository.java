package bjack.venusrestblog.repository;

import bjack.venusrestblog.data.Category;
import bjack.venusrestblog.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}