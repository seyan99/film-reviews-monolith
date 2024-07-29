package com.seyan.reviewmonolith.user;

import com.seyan.reviewmonolith.exception.user.UserNotFoundException;
import com.seyan.reviewmonolith.film.FilmRepository;
import com.seyan.reviewmonolith.user.dto.PageableUserResponseDTO;
import com.seyan.reviewmonolith.user.dto.UserCreationDTO;
import com.seyan.reviewmonolith.user.dto.UserMapper;
import com.seyan.reviewmonolith.user.dto.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final UserMapper userMapper;

    public User createUser(UserCreationDTO dto) {
        User user = userMapper.mapUserCreationDTOToUser(dto);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("No user found with the provided ID: %s", id)
        ));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(
                String.format("No user found with the provided username: %s", username)
        ));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //todo make pageable
    public List<User> getFollowingUsers(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("No user found with the provided ID: %s", id)
        ));

        return userRepository.findAllById(user.followingUsers);
    }

    //todo make pageable
    public List<User> getFollowersUsers(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("No user found with the provided ID: %s", id)
        ));

        return userRepository.findAllById(user.followersUsers);
    }

    public User updateUser(UserUpdateDTO dto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("Cannot update user:: No user found with the provided ID: %s", id)
        ));
        User mapped = userMapper.mapUserUpdateDTOToUser(dto, user);
        return userRepository.save(mapped);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("Cannot delete user:: No user found with the provided ID: %s", id)));
        userRepository.deleteById(id);
    }

    public PageableUserResponseDTO getAllUsersPageable(int pageNo, int pageSize) {
        return null;
    }
}
