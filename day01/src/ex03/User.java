package ex03;

public class User {
    private int id;
    private String name;
    private int balance;
    private TransactionsList transactions;

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

    public TransactionsList getTransactions() {
        return transactions;
    }

    public void setBalance(int balance) {
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransactions(TransactionsList transactions) {
        this.transactions = transactions;
    }
}
