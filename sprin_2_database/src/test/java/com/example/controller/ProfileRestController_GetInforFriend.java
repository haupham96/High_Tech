package com.example.controller;


import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileRestController_GetInforFriend {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getInforFriend_id_1() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/profile/friend/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getInforFriend_id_4() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/profile/friend/{id}", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.address").value("166 NX DN"))
                .andExpect(jsonPath("$.career").value("Freelance"))
                .andExpect(jsonPath("$.dateOfBirth").value("2000-01-01"))
                .andExpect(jsonPath("$.email").value("hau1@gmail.com"));
    }
}
