package com.weCare.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDefinition {

    private LocalDateTime time_stamp;

    private String message;

    private String description;

}
