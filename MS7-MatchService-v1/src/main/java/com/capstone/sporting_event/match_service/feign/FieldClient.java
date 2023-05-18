package com.capstone.sporting_event.match_service.feign;

import com.capstone.sporting_event.match_service.config.FeignClientConfiguration;
import com.capstone.sporting_event.match_service.models.Field;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "MS9-FieldService-v1" , configuration = FeignClientConfiguration.class)
public interface FieldClient {

    @GetMapping(value = "ms9/api/v1/field/name/{fieldName}")
    Field getFieldByFieldName(@PathVariable("fieldName") String fieldName);

    @GetMapping(value = "ms9/api/v1/field/id/{fieldId}")
    Field getFieldById(@PathVariable("fieldId") int fieldId);

}
