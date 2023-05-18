package com.capstone.sporting_event.user_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    int status;
    String message;
}
