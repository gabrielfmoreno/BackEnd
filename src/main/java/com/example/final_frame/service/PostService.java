package com.example.final_frame.service;

import com.example.final_frame.model.Post;
import com.example.final_frame.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post criarPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> listarPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> buscarPorId(Long id) {
        return postRepository.findById(id);
    }

    public Post atualizarPost(Post post) {
        return postRepository.save(post);
    }

    public void deletarPost(Long id) {
        postRepository.deleteById(id);
    }
}
