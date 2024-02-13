package com.test.Controller;


import com.test.entity.Post;
import com.test.payload.PostDto;
import com.test.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class PostController {
    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<Object> getById(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.getById(postDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteById(@RequestBody PostDto postDto) {
        postService.deleteById(postDto);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updatePost(@RequestBody PostDto postDto) {
        Post post = postService.updatePost(postDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping(value = "/detailByName")
    public Object getByName(@RequestBody PostDto postDto) {
        Object response;

        Map responsenew = new HashMap<>();

        try {
            response = postService.getByName(postDto);


            if (response == null) {
                throw new Exception();
            } else {
                responsenew.put("Detail By Search Name" + " --" + postDto.getName(), response);
            }
        } catch (Exception e) {
            responsenew.put("Detail By Search Name" + "-----" + postDto.getName() + "  NOT FOUND", 0);
            return new ResponseEntity<>(responsenew, HttpStatus.OK);
        }
        return new ResponseEntity<>(responsenew, HttpStatus.OK);
    }




}
