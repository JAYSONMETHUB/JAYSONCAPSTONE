package com.capstone.sporting_event.user_service;


import com.capstone.sporting_event.user_service.controller.UserController;
import com.capstone.sporting_event.user_service.dto.UserMapper;
import com.capstone.sporting_event.user_service.dto.UserRequestDTO;
import com.capstone.sporting_event.user_service.filter.JwtFilter;
import com.capstone.sporting_event.user_service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class,excludeAutoConfiguration = SecurityAutoConfiguration.class)

@Import(UserController.class)
@AutoConfigureMockMvc(addFilters = false)

class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    UserMapper userMapper;

    @MockBean
    JwtFilter jwtFilter;
    @Test
     void should_Get_All_Users() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms4/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
     void should_Get_Single_User() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms4/api/v1/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
     void should_Register_User() throws Exception
    {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("username")
                .password("password")
                .email("email@gmail.com")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/ms4/api/v1/register/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
                //.andExpect(content().string(SAVE_USER_SUCCESS));
    }

    @Test
     void should_Register_Admin() throws Exception
    {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("username")
                .password("password")
                .email("email@gmail.com")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ms4/api/v1/register/admin")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
        //.andExpect(content().string(SAVE_USER_SUCCESS));
    }

    @Test
     void should_Be_Able_To_Update_User() throws Exception
    {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("username")
                .password("password")
                .email("email@gmail.com")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/ms4/api/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)))
                .andExpect(status().isOk());
        //.andExpect(content().string(SAVE_USER_SUCCESS));
    }

    @Test
     void should_Be_Able_To_Delete_User() throws Exception
    {


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ms4/api/v1/user/1"))

                .andExpect(status().isAccepted());
        //.andExpect(content().string(SAVE_USER_SUCCESS));
    }


    private static String asJsonString(final Object obj){

        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
