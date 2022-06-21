package ex04;

import java.util.Arrays;
import java.util.UUID;

public class TransactionsService implements TransactionServiceInterface{
    private UsersList usersList;

    private TransactionsLinkedList unpairedTransactions = new TransactionsLinkedList();

    public TransactionsService() {
        this.usersList = new UsersArrayList();
    }

    public UsersList getUsersList() {
        return usersList;
    }

    public void setUsersList(UsersList usersList) {
        this.usersList = usersList;
    }

    public void addUser(User newUser) {
        usersList.addUser(newUser);
    }

    public int getBalance(int id) {
        return usersList.getUserById(id).getBalance();
    }

    public void makeTransfer(int recipientId, int senderId, int amount) {
        User recipient = usersList.getUserById(recipientId);
        User sender = usersList.getUserById(senderId);

        if (amount > sender.getBalance()) {
            throw new IllegalTransactionException("User " + senderId + " doesn't have enough money");
        }

        Transaction out = new Transaction(new UUID(amount, 1), recipient, sender, "OUTCOME", -amount);
        Transaction in = new Transaction(new UUID(amount, 1), sender, recipient, "INCOME", amount);

        recipient.getTransactions().addTransaction(in);
        sender.getTransactions().addTransaction(out);

        recipient.setBalance(recipient.getBalance() + amount);
        sender.setBalance((sender.getBalance() - amount));
    }

    @Override
    public Transaction[] getUserTransactions(int userId) {
        return usersList.getUserById(userId).getTransactions().transactionToArray();
    }

    @Override
    public void removeUserTransaction(int userId, UUID transactionId) {
        if (unpairedTransactions.isInList(transactionId)) {
            unpairedTransactions.removeTransactionById(transactionId);
        } else {
            unpairedTransactions.addTransaction(usersList.getUserById(userId).getTransactions().getTransactionById(transactionId));
        }

        usersList.getUserById(userId).getTransactions().removeTransactionById(transactionId);
    }

    @Override
    public Transaction[] checkTransactionCorrect() {
        System.out.println(Arrays.toString(unpairedTransactions.transactionToArray()));

        return unpairedTransactions.transactionToArray();
    }
}
