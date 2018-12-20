package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto implements Serializable {

    private String status;
    private String message;

    public ErrorResponseDto() {}

    public ErrorResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponseDto that = (ErrorResponseDto) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }
}
