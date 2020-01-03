package main.java.com.mkudryavtsev.crud2.dto;

public class BaseDto {
    private String errorMessage;
    private long id;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
