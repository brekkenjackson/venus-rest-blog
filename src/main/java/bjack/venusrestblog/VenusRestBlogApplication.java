package bjack.venusrestblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
public class VenusRestBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(VenusRestBlogApplication.class, args);
    }
}
