package com.capstone.sporting_event.user_service.models;

import com.capstone.sporting_event.user_service.dto.UserDisplayDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseWithBody
{

    int status;
    String message;
    UserDisplayDTO body;
}
