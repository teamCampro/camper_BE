package com.campro.common.controller.response;

public record ApiResponse<T> (String code, String status, T result) {

    public static <T> ApiResponse<T> from(String code, String status, T result) {
        return new ApiResponse<>(code, status, result);
    }

    public static <T> ApiResponse<T> from(String code, String status) {
        return new ApiResponse<>(code, status, null);
    }
}