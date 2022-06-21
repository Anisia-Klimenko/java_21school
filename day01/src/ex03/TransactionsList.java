package ex03;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction newTransaction);
    public void removeTransactionById(UUID id) throws TransactionNotFoundException;
    public Transaction[] transactionToArray();
}
