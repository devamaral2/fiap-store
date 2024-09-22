package br.com.fiap.auth.controller;

import br.com.fiap.auth.dto.GetRoleDto;
import br.com.fiap.auth.dto.UserDTO;
import br.com.fiap.auth.entity.User;
import br.com.fiap.auth.mapper.UserMapper;
import br.com.fiap.auth.repository.UserRepository;
import br.com.fiap.auth.security.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final SecurityFilter securityFilter;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> listUserDTO = repository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(listUserDTO);
    }

    @GetMapping("/role")
    public GetRoleDto getRoleId(HttpServletRequest request) {
        User user = securityFilter.getUserByToken(request);
        return new GetRoleDto(user.getRole());
    }

}
