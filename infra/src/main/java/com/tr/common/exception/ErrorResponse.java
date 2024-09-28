package com.tr.common.exception;

public record ErrorResponse(ErrorCode code,
                            String message) {
}
