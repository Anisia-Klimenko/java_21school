package ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User Mike = new User("Mike", 2000, null);
        User John = new User("John", 1500, null);

        TransactionsService transactionsService = new TransactionsService();
        transactionsService.addUser(Mike);
        transactionsService.addUser(John);

        System.out.println("Number of users: " + transactionsService.getUsersList().countUsers());
        System.out.println("Balance of user 0: " + transactionsService.getBalance(0));

        System.out.println("\nMike: " + Mike.getBalance() + ", John: " + John.getBalance());

        System.out.println("\nJohn -> Mike 500");
        transactionsService.makeTransfer(Mike.getId(), John.getId(), 500);
        System.out.println("Mike: " + Mike.getBalance() + ", John: " + John.getBalance());

        System.out.println("\nJohn -> Mike 600");
        transactionsService.makeTransfer(Mike.getId(), John.getId(), 600);
        System.out.println("Mike: " + Mike.getBalance() + ", John: " + John.getBalance());

        System.out.println("\nArray of transactions of user 0 (" + Mike.getName() + "):");
        for (Transaction tr : transactionsService.getUserTransactions(0)) {
            System.out.println("-> " + tr.getTransferAmount() + " -> " + tr.getId());
        }

        transactionsService.removeUserTransaction(Mike.getId(), new UUID(500, 1));
        System.out.println("\nNew array of transactions of user 0 (" + Mike.getName() + "):");

        for (Transaction tr : transactionsService.getUserTransactions(0)) {
            System.out.println("-> " + tr.getTransferAmount() + " -> " + tr.getId());
        }

        System.out.println("\nUnpaired transactions: ");
        for (Transaction tr : transactionsService.checkTransactionCorrect()) {
            System.out.println("-> " + tr.getId());
        }

        System.out.println("\nJohn -> Mike 6000 -> EXCEPTION");
        transactionsService.makeTransfer(Mike.getId(), John.getId(), 6000);
        System.out.println("Mike: " + Mike.getBalance() + ", John: " + John.getBalance());
    }
}
