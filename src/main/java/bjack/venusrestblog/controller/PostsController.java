package bjack.venusrestblog.controller;

import bjack.venusrestblog.data.Category;
import bjack.venusrestblog.data.Post;

import bjack.venusrestblog.data.User;
import bjack.venusrestblog.misc.FieldHelper;
import bjack.venusrestblog.repository.CategoriesRepository;
import bjack.venusrestblog.repository.PostsRepository;
import bjack.venusrestblog.repository.UsersRepository;
import bjack.venusrestblog.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostsController {
    private EmailService emailService;
    private PostsRepository postsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;

    @GetMapping("")
    public List<Post> fetchPosts() {
        return postsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> fetchPostById(@PathVariable long id) {
        Optional<Post> optionalPost = postsRepository.findById(id);
        if(optionalPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id " + id + " not found");
        }
        return optionalPost;
    }

    @PostMapping("")
    public void createPost(@RequestBody Post newPost) {

        User author = usersRepository.findById(3L).get();
        newPost.setAuthor(author);
//        newPost.setCategories(new ArrayList<>());
//        Category cat1 = categoriesRepository.findById(1L).get();
//        Category cat2 = categoriesRepository.findById(2L).get();
//
//        newPost.getCategories().add(cat1);
//        newPost.getCategories().add(cat2);

        postsRepository.save(newPost);

        emailService.prepareAndSend(newPost, "Hey man you made a post", "See subject");

    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable long id) {
        Optional<Post> optionalPost = postsRepository.findById(id);
        if(optionalPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id " + id + " not found");
        }
        postsRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@RequestBody Post updatedPost, @PathVariable long id) {
        Optional<Post> originalPost = postsRepository.findById(id);
        if(originalPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + " not found");
        }

        // in case id is not in the request body (i.e., updatedPost), set it
        // with the path variable id
        updatedPost.setId(id);

        // copy any new field values FROM updatedPost TO originalPost
        BeanUtils.copyProperties(updatedPost, originalPost.get(), FieldHelper.getNullPropertyNames(updatedPost));

        postsRepository.save(originalPost.get());
    }
}