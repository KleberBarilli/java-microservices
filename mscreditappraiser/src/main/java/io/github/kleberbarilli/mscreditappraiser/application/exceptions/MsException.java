package io.github.kleberbarilli.mscreditappraiser.application.exceptions;

import lombok.Getter;

public class MsException extends Exception {

    @Getter
    private Integer status;

    public MsException(String message, Integer status) {
        super(message);

        this.status = status;

    }
}
