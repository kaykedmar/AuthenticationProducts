package com.example.demo.service;

import com.example.demo.dtos.UserRegisterDTO;
import com.example.demo.dtos.UserResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.EmailAlreadyExistsException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponseDTO registerUser(UserRegisterDTO dto){

        if (userRepository.existsByEmail(dto.email())){
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }

        User user = new User();
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Collections.singleton(Role.ROLE_ADMIN));

        User userSaved = userRepository.save(user);


        return userMapper.toResponseDTO(userSaved);
    }

}
