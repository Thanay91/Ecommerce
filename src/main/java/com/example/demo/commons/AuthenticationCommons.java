package com.example.demo.commons;

import com.example.demo.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    @Autowired
    private RestTemplate restTemplate;

    public UserDTO validateToken(String tokenValue){
        ResponseEntity<UserDTO> apiResponse = restTemplate.getForEntity("http://localhost:9000/users/validate/"+tokenValue ,
                UserDTO.class);

        return apiResponse.getBody();
    }

}
