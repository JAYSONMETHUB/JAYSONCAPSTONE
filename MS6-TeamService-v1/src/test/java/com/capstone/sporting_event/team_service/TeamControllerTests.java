package com.capstone.sporting_event.team_service;




import com.capstone.sporting_event.team_service.controller.TeamController;
import com.capstone.sporting_event.team_service.dto.TeamMapper;
import com.capstone.sporting_event.team_service.dto.TeamRequestDTO;
import com.capstone.sporting_event.team_service.service.TeamService;
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

@WebMvcTest(controllers = TeamController.class)
@Import(TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
class TeamControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TeamService teamService;

    @MockBean
    TeamMapper teamMapper;

    @Test
     void should_Get_All_Teams() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms6/api/v1/teams")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
     void should_Get_Single_Team() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms6/api/v1/team/id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
     void should_create_Team() throws Exception
    {
        TeamRequestDTO teamRequest = TeamRequestDTO.builder()
                .teamName("teamname")

                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/ms6/api/v1/team")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(teamRequest)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
                //.andExpect(content().string(SAVE_TEAM_SUCCESS));
    }

    @Test
     void should_Be_Able_To_Update_Team() throws Exception
    {
        TeamRequestDTO teamRequest = TeamRequestDTO.builder()
                .teamName("jayson")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/ms6/api/v1/team/1")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(teamRequest)))
                .andExpect(status().isOk());
        //.andExpect(content().string(SAVE_TEAM_SUCCESS));
    }

    @Test
     void should_Be_Able_To_Delete_Team() throws Exception
    {


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ms6/api/v1/team/1"))

                .andExpect(status().isAccepted());
        //.andExpect(content().string(SAVE_TEAM_SUCCESS));
    }


    private static String asJsonString(final Object obj){

        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
