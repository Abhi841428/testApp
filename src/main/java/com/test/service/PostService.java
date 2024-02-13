package com.test.service;


import com.test.Exception.ResourceNotFoundException;
import com.test.entity.Post;
import com.test.payload.PostDto;
import com.test.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;

//    public PostService( ModelMapper mapper) {
//
//        this.mapper = mapper;
//    }
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);
        Post savedData = postRepository.save(post);
        postDto.setId(savedData.getId());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);

        return post;
    }


    public Object getById(PostDto postDto) {

       Post post =postRepository.findById(postDto.getId()).orElseThrow(()-> new ResourceNotFoundException("detail Not Found","ID ",postDto.getId()));
        return post;

    }

    public void deleteById(PostDto postDto) {
        postRepository.deleteById(postDto.getId());
    }

    public Post updatePost(PostDto postDto) {
        Long id = postDto.getId();
            Post post = postRepository.findById(id).orElseThrow();


        post.setName(postDto.getName());
        post.setFatherName(postDto.getFatherName());
        post.setCourse(postDto.getCourse());
        postRepository.save(post);
        return post;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Object getByName(PostDto postDto) {
        return postRepository.findByName(postDto.getName());
    }


}
