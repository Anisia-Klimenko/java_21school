package ex05;

import java.util.UUID;

public interface TransactionServiceInterface {
    public void addUser(User newUser);
    public Integer getBalance(Integer userId);
    public void makeTransfer(Integer recipientId, Integer senderId, Integer amount) throws IllegalTransactionException;
    public Transaction[] getUserTransactions(Integer userId);
    public void removeUserTransaction(Integer userId, UUID transactionId) throws TransactionNotFoundException ;
    public Transaction[] checkTransactionCorrect();
}
