package com.example.oauth2google.controller;

import com.example.oauth2google.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response defaultMethod() {
//        ObjectMapper objectMapper = new ObjectMapper();
        Response response = new Response();
        response.setId(1);
        response.setName("Hau Pham");
        response.setAddress("166 Nguyen Xi");
        return response;
    }
}
