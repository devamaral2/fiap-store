package br.com.fiap.auth.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.auth.dto.UserDTO;
import br.com.fiap.auth.mapper.UserMapper;
import br.com.fiap.auth.repository.UserRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
    	
    	List<UserDTO> listUserDTO = repository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(listUserDTO);
    }

}
