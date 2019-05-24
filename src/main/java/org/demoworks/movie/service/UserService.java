package org.demoworks.movie.service;

import org.demoworks.movie.model.User;
import org.demoworks.movie.repository.UserRepository;
import org.demoworks.movie.web.dto.UserDto;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserDto create(UserDto userDto){
       return UserDto.fromEntity(userRepository.save(UserDto.toEntitiy(userDto)));
    }
}