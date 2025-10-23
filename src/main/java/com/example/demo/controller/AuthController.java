package com.example.demo.controller;

import com.example.demo.dtos.UserLoginDTO;
import com.example.demo.dtos.UserRegisterDTO;
import com.example.demo.dtos.UserResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO dto){
        UserResponseDTO user = userService.registerUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginDTO dto){

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                );

        // autenticando o token email, password
        Authentication authentication = authenticationManager.authenticate(authToken);

        // guarda informações do usuário autenticado
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // recuperando o CustomUserDetails
        // principal → representa quem é o usuário autenticado
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // com Mapper
        UserResponseDTO responseDTO = userMapper.toResponseDTO(userDetails.getUser());

        // manual (sem mapper)
/*        UserResponseDTO response = new UserResponseDTO(
                userDetails.getUser().getId(),
                userDetails.getUser().getUsername(),
                userDetails.getUser().getEmail(),
                userDetails.getUser().getRoles()
        );*/

        return ResponseEntity.ok(responseDTO);

    }


}
