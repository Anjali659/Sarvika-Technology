package com.sarvika.service;

import com.sarvika.dto.UserRequestDTO;
import com.sarvika.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    UserResponseDTO saveUser(UserRequestDTO user);

    String updateUsers(int id, UserRequestDTO users);

    UserResponseDTO fetchUser(int id);

    List<UserResponseDTO> fetchAllUser();

    String deleteUsers(int id);
}
