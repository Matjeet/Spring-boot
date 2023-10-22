package com.mateo.ec.miprimerapirest.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ResponseMessage implements Serializable {

    private String message;
    private Object object;
}
