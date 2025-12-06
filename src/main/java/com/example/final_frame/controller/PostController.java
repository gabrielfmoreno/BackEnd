package com.example.final_frame.controller;

import com.example.final_frame.model.Post;
import com.example.final_frame.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post criarPost(@RequestBody Post post) {
        return postService.criarPost(post);
    }

    @GetMapping
    public List<Post> listarPosts() {
        return postService.listarPosts();
    }

    @GetMapping("/{id}")
    public Optional<Post> buscarPost(@PathVariable Long id) {
        return postService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Post atualizarPost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        return postService.atualizarPost(post);
    }

    @DeleteMapping("/{id}")
    public void deletarPost(@PathVariable Long id) {
        postService.deletarPost(id);
    }
}
