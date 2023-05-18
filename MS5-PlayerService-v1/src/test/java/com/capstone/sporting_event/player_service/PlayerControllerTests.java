package com.capstone.sporting_event.player_service;



import com.capstone.sporting_event.player_service.controller.PlayerController;
import com.capstone.sporting_event.player_service.dto.PlayerMapper;
import com.capstone.sporting_event.player_service.dto.PlayerRequestDTO;
import com.capstone.sporting_event.player_service.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

@WebMvcTest(controllers = PlayerController.class)
@Import(PlayerController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlayerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlayerService playerService;

    @MockBean
    PlayerMapper playerMapper;

    @Test
     void should_Get_All_Players() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms5/api/v1/players")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
     void should_Get_Single_Player() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms5/api/v1/player/id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
     void should_create_Player() throws Exception
    {
        PlayerRequestDTO playerRequest = PlayerRequestDTO.builder()
                .firstName("playername")
                .lastName("lastname")
                .country("philippines")
                .teamName("test name")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/ms5/api/v1/player")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(playerRequest)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
                //.andExpect(content().string(SAVE_PLAYER_SUCCESS));
    }

    @Test
     void should_Be_Able_To_Update_Player() throws Exception
    {
        PlayerRequestDTO playerRequest = PlayerRequestDTO.builder()
                .firstName("jayson")
                .lastName("concepcion")
                .country("philippines")
                .teamName("testname")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/ms5/api/v1/player/1")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(playerRequest)))
                .andExpect(status().isOk());
        //.andExpect(content().string(SAVE_PLAYER_SUCCESS));
    }

    @Test
     void should_Be_Able_To_Delete_Player() throws Exception
    {


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ms5/api/v1/player/1"))

                .andExpect(status().isAccepted());
        //.andExpect(content().string(SAVE_PLAYER_SUCCESS));
    }


    private static String asJsonString(final Object obj){

        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
