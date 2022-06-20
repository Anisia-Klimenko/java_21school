package ex01;

public class User {
    private int id;
    private String name;
    private int balance;

    public User() {
        this.id = UserIdsGenerator.getInstance().generateId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
