package com.example.usermanagement.service;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id! " + id));
    }

    public List<User> getAllUsersByLastNameAndDateOfBirth(String lastName, Date dateOfBirth) {
        return userRepository.findAllUsersSortedByLastNameAndDateOfBirth(lastName, dateOfBirth);
    }

    public List<User> searchUsers(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public User updateUser(Long id, User updatedUser) throws UserNotFoundException {
        User user = getUserById(id);
        // Update user fields based on the updatedUser object

        user.setId(id);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmailAddress(updatedUser.getEmailAddress());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setEmailAddress(updatedUser.getEmailAddress());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
