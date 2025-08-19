package com.infinite.service.imp;

import com.infinite.exception.UserException;
import com.infinite.modal.User;
import com.infinite.repository.UserRepository;
import com.infinite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> otp=userRepository.findById(id);
        if(otp.isPresent()){
            return otp.get();
        }
        throw new UserException("User not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User>otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new UserException("User not found with id "+id);
        }
        userRepository.deleteById(otp.get().getId());

    }

    @Override
    public User updateUser(Long id, User user) throws UserException {

        Optional<User>otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new UserException("User not found with "+id);
        }
        User existingUser=otp.get();

        existingUser.setFullname(user.getFullname());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setPhone(user.getPhone());
        existingUser.setUsername(user.getUsername());
        return userRepository.save(existingUser);
    }
}
