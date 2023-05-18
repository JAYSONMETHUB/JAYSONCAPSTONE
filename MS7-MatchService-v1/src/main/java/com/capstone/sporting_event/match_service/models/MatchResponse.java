package com.capstone.sporting_event.match_service.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponse {
    int status;
    String message;
}
