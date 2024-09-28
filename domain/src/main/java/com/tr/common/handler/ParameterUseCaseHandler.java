package com.tr.common.handler;

public interface ParameterUseCaseHandler<T, U> {
    T handle(U parameter);
}
