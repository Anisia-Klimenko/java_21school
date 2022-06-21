package ex04;

import java.util.UUID;

public interface TransactionServiceInterface {
    public void addUser(User newUser);
    public int getBalance(int userId);
    public void makeTransfer(int recipientId, int senderId, int amount) throws TransactionNotFoundException;
    public Transaction[] getUserTransactions(int userId);
    public void removeUserTransaction(int userId, UUID transactionId);
    public Transaction[] checkTransactionCorrect();
}
