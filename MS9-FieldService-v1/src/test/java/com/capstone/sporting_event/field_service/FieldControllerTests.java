package com.capstone.sporting_event.field_service;




import com.capstone.sporting_event.field_service.controller.FieldController;
import com.capstone.sporting_event.field_service.dto.FieldMapper;
import com.capstone.sporting_event.field_service.dto.FieldRequestDTO;
import com.capstone.sporting_event.field_service.service.FieldService;
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

@WebMvcTest(controllers = FieldController.class)
@Import(FieldController.class)
@AutoConfigureMockMvc(addFilters = false)
class FieldControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FieldService fieldService;

    @MockBean
    FieldMapper fieldMapper;

    @Test
    public void should_Get_All_Fields() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms9/api/v1/fields")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void should_Get_Single_Field() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ms9/api/v1/field/id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_create_Field() throws Exception
    {
        FieldRequestDTO fieldRequest = FieldRequestDTO.builder()
                .fieldName("fieldname")
                .fieldAddress("testaddress")
                .capacity(100)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/ms9/api/v1/field")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(fieldRequest)))
                .andExpect(status().is(HttpStatus.CREATED.value()));
                //.andExpect(content().string(SAVE_FIELD_SUCCESS));
    }

    @Test
    public void should_Be_Able_To_Update_Field() throws Exception
    {
        FieldRequestDTO fieldRequest = FieldRequestDTO.builder()
                .fieldName("jayson")
                .fieldAddress("test")
                .capacity(1)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/ms9/api/v1/field/1")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(fieldRequest)))
                .andExpect(status().isOk());
        //.andExpect(content().string(SAVE_FIELD_SUCCESS));
    }

    @Test
    public void should_Be_Able_To_Delete_Field() throws Exception
    {


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ms9/api/v1/field/1"))

                .andExpect(status().isAccepted());
        //.andExpect(content().string(SAVE_FIELD_SUCCESS));
    }


    private static String asJsonString(final Object obj){

        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
