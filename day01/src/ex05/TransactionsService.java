package ex05;

import java.util.UUID;

public class TransactionsService implements TransactionServiceInterface {
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

    public Integer getBalance(Integer id) {
        return usersList.getUserById(id).getBalance();
    }

    public void makeTransfer(Integer recipientId, Integer senderId, Integer amount) {
        User recipient = usersList.getUserById(recipientId);
        User sender = usersList.getUserById(senderId);

        if (amount > sender.getBalance()) {
            throw new IllegalTransactionException("User " + sender.getName() + " (id = "+ senderId
                    + ") doesn't have enough money");
        }

        UUID trId = UUID.randomUUID();

        Transaction out = new Transaction(trId, recipient, sender, "OUTCOME", -amount);
        Transaction in = new Transaction(trId, sender, recipient, "INCOME", amount);

        recipient.getTransactions().addTransaction(in);
        sender.getTransactions().addTransaction(out);

        recipient.setBalance(recipient.getBalance() + amount);
        sender.setBalance((sender.getBalance() - amount));
    }

    @Override
    public Transaction[] getUserTransactions(Integer userId) throws TransactionNotFoundException {
        return usersList.getUserById(userId).getTransactions().transactionToArray();
    }

    @Override
    public void removeUserTransaction(Integer userId, UUID transactionId) {
        if (unpairedTransactions.isInList(transactionId)) {
            unpairedTransactions.removeTransactionById(transactionId);
        } else {
            if (usersList.getUserById(userId).getTransactions().getTransactionById(transactionId) != null) {
                unpairedTransactions.addTransaction(usersList.getUserById(userId).getTransactions().getTransactionById(transactionId));
            } else {
                throw new TransactionNotFoundException("Transaction id = " + transactionId + " not found");
            }
        }

        usersList.getUserById(userId).getTransactions().removeTransactionById(transactionId);
    }

    @Override
    public Transaction[] checkTransactionCorrect() {
        return unpairedTransactions.transactionToArray();
    }
}
