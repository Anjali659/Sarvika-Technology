package com.sarvika.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvika.dto.UserRequestDTO;
import com.sarvika.dto.UserResponseDTO;
import com.sarvika.entity.Users;
import com.sarvika.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements  UserService{

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public UserResponseDTO saveUser(UserRequestDTO user) {
      Users users=  userRepository.save(objectMapper.convertValue(user, Users.class));
        return objectMapper.convertValue(users,UserResponseDTO.class);
    }

    @Override
    public String updateUsers(int id, UserRequestDTO userRequestDTO) {
        return userRepository.findById(id).map(existingUser -> {

            if (userRequestDTO.getUsername() != null) {
                existingUser.setUsername(userRequestDTO.getUsername());
            }
            if (userRequestDTO.getPassword() != null) {
                existingUser.setPassword(userRequestDTO.getPassword());
            }
            if (userRequestDTO.getAddress() != null) {
                existingUser.setAddress(userRequestDTO.getAddress());
            }
            if (userRequestDTO.getAge() != 0) {
                existingUser.setAge(userRequestDTO.getAge());
            }
            if (userRequestDTO.getGender() != null) {
                existingUser.setGender(userRequestDTO.getGender());
            }
            if (userRequestDTO.getOccupation() != null) {
                existingUser.setOccupation(userRequestDTO.getOccupation());
            }

            userRepository.save(existingUser);
            return "User updated successfully";
        }).orElse("User not found with id: " + id);
    }

    @Override
    public UserResponseDTO fetchUser(int id) {
        return objectMapper.convertValue(userRepository.findById(id).get(),UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> fetchAllUser() {
        List<Users> list = userRepository.findAll();
        List<UserResponseDTO> responseList = new ArrayList<>();

        for (Users user : list) {
            UserResponseDTO dto = objectMapper.convertValue(user,UserResponseDTO.class);
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public String deleteUsers(int id){
        try{
            userRepository.deleteById(id);
            return  id +" user delete sucessfully";
        }
        catch (Exception e) {
            return id + "USer Not Found";
        }
    }


}
