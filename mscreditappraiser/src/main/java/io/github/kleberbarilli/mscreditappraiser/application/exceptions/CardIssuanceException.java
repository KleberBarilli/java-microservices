package io.github.kleberbarilli.mscreditappraiser.application.exceptions;

public class CardIssuanceException extends RuntimeException {
    public CardIssuanceException(String message) {
        super(message);
    }
}
