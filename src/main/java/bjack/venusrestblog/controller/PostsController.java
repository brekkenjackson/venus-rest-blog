package bjack.venusrestblog.controller;

import bjack.venusrestblog.data.Post;
import bjack.venusrestblog.data.Category;

import bjack.venusrestblog.data.User;
import bjack.venusrestblog.repository.CategoriesRepository;
import bjack.venusrestblog.repository.PostsRepository;
import bjack.venusrestblog.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostsController {
    private PostsRepository postsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;

    @GetMapping("")
    public List<Post> fetchPosts() {
        return postsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> fetchPostById(@PathVariable long id) {
        return postsRepository.findById(id);
    }

    @PostMapping("")
    public void createPost(@RequestBody Post newPost) {

        // use a fake author for the post
        User author = usersRepository.findById(1L).get();
        newPost.setAuthor(author);

        Category cat1 = categoriesRepository.findById(1L).get();
        Category cat2 = categoriesRepository.findById(2L).get();
        newPost.setCategories(new ArrayList<>());
        newPost.getCategories().add(cat1);
        newPost.getCategories().add(cat2);

        postsRepository.save(newPost);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable long id) {
        postsRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@RequestBody Post updatedPost, @PathVariable long id) {
        // in case id is not in the request body (i.e., updatedPost), set it
        // with the path variable id
        updatedPost.setId(id);
        postsRepository.save(updatedPost);

    }
}