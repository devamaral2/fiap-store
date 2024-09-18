package br.com.fiap.auth.mapper;

import org.mapstruct.Mapper;

import br.com.fiap.auth.dto.UserDTO;
import br.com.fiap.auth.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDTO toDTO(User user);
    
	User toUser(UserDTO userDTO);

}
