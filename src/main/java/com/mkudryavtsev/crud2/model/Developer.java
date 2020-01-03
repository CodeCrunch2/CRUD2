package main.java.com.mkudryavtsev.crud2.model;

public class Developer extends BaseEntity {
    private Account account;

    public Developer( Account account) {

        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
