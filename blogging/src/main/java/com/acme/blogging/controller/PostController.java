package com.acme.blogging.controller;

import com.acme.blogging.exception.ResourceNotFoundException;
import com.acme.blogging.model.Post;
import com.acme.blogging.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.validation.Valid;
import java.nio.file.ReadOnlyFileSystemException;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId,@Valid @RequestBody Post postUpdate){
        return postRepository.findById(postId).map(post->{
            post.setTittle(postUpdate.getTittle());
            post.setDescription(postUpdate.getDescription());
            post.setContent(postUpdate.getContent());
            return postRepository.save(post);
        }).orElseThrow(()->new ResourceNotFoundException("Post not found"));

    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        return postRepository.findById(postId).map(post ->{
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Post not found"));
    }


}
