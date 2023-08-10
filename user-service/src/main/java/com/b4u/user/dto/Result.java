package com.b4u.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int statusCode = 500;
    private Object data;

}
