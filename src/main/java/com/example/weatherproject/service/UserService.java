package com.example.weatherproject.service;

import com.example.weatherproject.dto.LoginDto;
import com.example.weatherproject.dto.UserCreatDto;
import com.example.weatherproject.dto.UserResponseDto;
import com.example.weatherproject.module.UserEntity;
import com.example.weatherproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   public UserResponseDto login(LoginDto loginDto){
       UserEntity user = userRepository.findByEmail(loginDto.getEmail())
               .orElseThrow(() -> new RuntimeException("Email or password incorrect"));
       if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
           throw new RuntimeException("Email or password incorrect");
       }
       UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
               user.getUsername(), null, user.getAuthorities()
       );
       SecurityContextHolder.getContext().setAuthentication(auth);
       return mapUserToResponse(user);

   }

    private UserResponseDto mapUserToResponse(UserEntity user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserResponseDto register(UserCreatDto creatDto){
       if(!creatDto.getPassword().equals(creatDto.getConfirmPassword()))
           throw new RuntimeException("Incorrect confirm password");
       if (userRepository.existsByEmail(creatDto.getEmail())) {
           throw new RuntimeException("Email already exists");
       }
       UserEntity user = UserEntity.builder().email(creatDto.getEmail()).password(creatDto.getPassword()).build();
       userRepository.save(user);
       return mapUserToResponse(user);
   }

    public List<UserResponseDto> getAll(Integer page , Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> all = userRepository.findAll(pageable);
        return all.get()
                .map(this::mapUserToResponse)
                .collect(Collectors.toList());
    }

}
