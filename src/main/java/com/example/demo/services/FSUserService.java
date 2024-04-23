package com.example.demo.services;

import com.example.demo.dtos.UserResponseDTO;
import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FSUserService implements IUserService{
    @Autowired
    RestTemplate restTemplate;

    public User getUserObjectFromResponseDTO(UserResponseDTO responseDTO){
        User user = new User();
        user.setId(responseDTO.getId());

        FullName name = new FullName();
        name.setFirstName(responseDTO.getFullname().getFirstname());
        name.setLastName(responseDTO.getFullname().getLastname());
        user.setFullName(name);
        user.setUserName(responseDTO.getUsername());

        Address address = new Address();
        address.setCity(responseDTO.getAddress().getCity());
        address.setNumber(responseDTO.getAddress().getNumber());
        address.setStreet(responseDTO.getAddress().getStreet());
        address.setZipCode(responseDTO.getAddress().getZipcode());

        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setLat(responseDTO.getAddress().getGeolocation().getLat());
        geoLocation.setLng(responseDTO.getAddress().getGeolocation().getLng());
        address.setGeoLocation(geoLocation);

        user.setAddress(address);

        user.setEmail(responseDTO.getEmail());
        user.setPassword(responseDTO.getPassword());
        user.setPhone(responseDTO.getPhone());

        return user;




    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        UserResponseDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/users",
                UserResponseDTO[].class);

        for(UserResponseDTO res: response){
            User user = getUserObjectFromResponseDTO(res);
            users.add(user);
        }

        return users;

    }
}
