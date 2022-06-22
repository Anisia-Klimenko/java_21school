package ex03;

public class User {
    private Integer id;
    private String name;
    private Integer balance;
    private TransactionsList transactions;

    public User() {
        this.id = UserIdsGenerator.getInstance().generateId();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public TransactionsList getTransactions() {
        return transactions;
    }

    public void setBalance(Integer balance) {
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
