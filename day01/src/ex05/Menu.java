package ex05;

import java.util.UUID;

public class Menu{
    private TransactionsService service = new TransactionsService();

    public Menu() {
        this.service = service;
    }

    public void showMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
    }

    public void addUser(String line) throws IllegalArgumentException {
        String[] split = line.split("\\s+");

        if (split.length != 2) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }

        User newUser = new User(split[0], Integer.parseInt(split[1]), null);

        Integer id = newUser.getId();

        service.addUser(newUser);
        System.out.println("User with id = " + id + " is added");
    }

    public void getBalance(String line) {
        Integer userId = Integer.parseInt(line);
        System.out.println(service.getUsersList().getUserById(userId).getName() + " - " + service.getBalance(userId));
    }

    public void makeTransfer(String line) throws IllegalTransactionException, IllegalArgumentException {
        String[] split = line.split("\\s+");

        if (split.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }

        try {
            service.makeTransfer(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            System.out.println("The transfer is completed");
        } catch (IllegalTransactionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserTransactions(String line) {
        Integer userId = Integer.parseInt(line);
        Transaction[] transactions = service.getUserTransactions(userId);

        for (Transaction tr : transactions) {
            if (tr.getTransferCategory().equals("INCOME")) {
                System.out.print("From ");
            } else if (tr.getTransferCategory().equals("OUTCOME")) {
                System.out.print("To ");
            }

            if (tr.getSender().getId() == userId) {
                System.out.print(tr.getRecipient().getName() + "(id = " + tr.getRecipient().getId() + ") ");
            } else if (tr.getRecipient().getId() == userId) {
                System.out.print(tr.getSender().getName() + "(id = " + tr.getSender().getId() + ") ");
            }

            System.out.println(tr.getTransferAmount() + " with id = " + tr.getId());
        }
    }

    public void removeUserTransaction(String line) throws IllegalArgumentException {
        String[] split = line.split("\\s+");

        if (split.length != 2) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }

        Integer userId = Integer.parseInt(split[0]);
        UUID transactionId = UUID.fromString(split[1]);

        String trCategory = null;
        String name = null;
        Integer id = 0;
        Integer trAmount = 0;

        Transaction[] transactions = service.getUserTransactions(userId);

        for (Transaction tr : transactions) {
            if (tr.getId().equals(transactionId)) {
                if (tr.getTransferCategory().equals("INCOME")) {
                    trCategory = "from ";
                    name = tr.getSender().getName();
                    id = tr.getSender().getId();
                } else if (tr.getTransferCategory().equals("OUTCOME")) {
                    trCategory = "to ";
                    name = tr.getRecipient().getName();
                    id = tr.getRecipient().getId();
                }

                trAmount = tr.getTransferAmount();
            }
        }

        service.removeUserTransaction(userId, transactionId);
        System.out.println("Transfer " + trCategory + name + " (id = " + id + ") " + trAmount + " removed");
    }

    public void checkTransactionCorrect() {
        Transaction[] transactions = service.checkTransactionCorrect();

        System.out.println("Check results:");

        for (Transaction tr : transactions) {
            if (tr.getTransferCategory().equals("OUTCOME")) {
                System.out.print(tr.getSender().getName() + " (id = " + tr.getSender().getId() + ") has an ");
                System.out.print("unacknowledged transfer id = " + tr.getId() + " to " + tr.getRecipient().getName());
                System.out.println(" (id = " + tr.getRecipient().getId() + ") for " + tr.getTransferAmount());
            } else if (tr.getTransferCategory().equals("INCOME")) {
                System.out.print(tr.getRecipient().getName() + " (id = " + tr.getRecipient().getId() + ") has an ");
                System.out.print("unacknowledged transfer id = " + tr.getId() + " from " + tr.getSender().getName());
                System.out.println(" (id = " + tr.getSender().getId() + ") for " + (-tr.getTransferAmount()));
            }
        }

        if (transactions == null) {
            System.out.println("No results");
        }
    }
}
