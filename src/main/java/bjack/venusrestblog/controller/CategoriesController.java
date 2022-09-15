package bjack.venusrestblog.controller;

import bjack.venusrestblog.data.Category;
import bjack.venusrestblog.data.Post;
import bjack.venusrestblog.repository.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/categories", produces = "application/json")
public class CategoriesController {
    private CategoriesRepository categoriesRepository;

    @GetMapping("")
    private List<Category> fetchPostsByCategory(@RequestParam String categoryName) {


        return categoriesRepository.findAll();
    }

}