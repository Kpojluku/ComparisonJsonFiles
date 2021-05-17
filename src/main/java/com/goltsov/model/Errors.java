package com.goltsov.model;

public class Errors {
    private boolean isJsonParseException;
    private boolean isInvalidFormatException;

    public boolean isInvalidFormatException() {
        return isInvalidFormatException;
    }

    public void setInvalidFormatException(boolean invalidFormatException) {
        isInvalidFormatException = invalidFormatException;
    }

    public boolean isJsonParseException() {
        return isJsonParseException;
    }

    public void setJsonParseException(boolean jsonParseException) {
        isJsonParseException = jsonParseException;
    }
}
