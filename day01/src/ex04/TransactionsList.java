package ex04;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction newTransaction);
    public void removeTransactionById(UUID id) throws TransactionNotFoundException;
    public Transaction[] transactionToArray();
    public boolean isInList(UUID id);
    public Transaction getTransactionById(UUID id);
}
