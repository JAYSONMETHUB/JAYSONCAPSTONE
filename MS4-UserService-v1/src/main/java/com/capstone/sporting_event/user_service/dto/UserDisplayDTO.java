package com.capstone.sporting_event.user_service.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDisplayDTO {

    private int userId;

    private String userName;

    private String email;

    private String role;
}
