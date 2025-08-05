package com.tata.demo.application.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectsResponse<T> {

    private Integer code;

    private String status;

    private String message;

    private T object;
}
