package com.capstone.sporting_event.match_service.models;

import com.capstone.sporting_event.match_service.dto.MatchDisplayDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponseWithBody
{

    int status;
    String message;
    MatchDisplayDTO body;
}
