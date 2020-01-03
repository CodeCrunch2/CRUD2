package main.java.com.mkudryavtsev.crud2.model;

public class Account extends BaseEntity {
    private String userName;

    public Account(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
