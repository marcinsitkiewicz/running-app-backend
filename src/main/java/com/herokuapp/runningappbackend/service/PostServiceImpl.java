package com.herokuapp.runningappbackend.service;

import com.herokuapp.runningappbackend.dto.PostDTO;
import com.herokuapp.runningappbackend.exception.NoDataException;
import com.herokuapp.runningappbackend.model.Activity;
import com.herokuapp.runningappbackend.model.Post;
import com.herokuapp.runningappbackend.model.User;
import com.herokuapp.runningappbackend.repository.ActivityRepository;
import com.herokuapp.runningappbackend.repository.PostRepository;
import com.herokuapp.runningappbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class PostServiceImpl implements IService<PostDTO> {

    private final PostRepository postRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ActivityServiceImpl activityService;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,
                           ActivityRepository activityRepository,
                           UserRepository userRepository,
                           ActivityServiceImpl activityService,
                           ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.activityService = activityService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Collection<PostDTO> getAll() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        return modelMapper.map(posts, new TypeToken<List<PostDTO>>(){}.getType());
    }

    @Override
    public PostDTO get(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NoDataException::new);

        return modelMapper.map(post, PostDTO.class);
    }

    public Collection<PostDTO> queryAll(Specification<Post> specs) {
        List<Post> posts = postRepository.findAll(Specification.where(specs));

        return modelMapper.map(posts, new TypeToken<List<PostDTO>>(){}.getType());
    }

    public void create(Long activityId, Long userId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(NoDataException::new);
        User user = userRepository.findById(userId).orElseThrow(NoDataException::new);

        Post post = new Post();
        post.setPostAuthor(user);
        post.setPostedDate(LocalDateTime.now());
//        post.setActivity(activity);

//        Gson gson = new Gson();
//        System.out.println("ACTIVITY: \n" + gson.toJson(activityService.get(activityId)));
//        System.out.println("USER: \n" + gson.toJson(modelMapper.map(user, UserDTO.class)));
//        System.out.println("POST: \n" + gson.toJson(modelMapper.map(post, PostDTO.class)));

         postRepository.save(post);
    }

    public void create(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        postRepository.save(post);
    }
}
