package io.github.kleberbarilli.mscreditappraiser.application.exceptions;

public class NotFoundException extends Exception {

    private String resource;

    public NotFoundException(String resource) {
        super(resource + "not found");
    }
}
