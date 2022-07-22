package ex03;

import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User Mike = new User("Mike", 1000, null);
        User John = new User("John", 2000, null);

        Transaction tr1 = new Transaction(UUID.randomUUID(), Mike, John, "INCOME", 200);
        Transaction tr2 = new Transaction(UUID.randomUUID(), John, Mike, "OUTCOME", -500);
        Transaction tr3 = new Transaction(UUID.randomUUID(), John, Mike, "OUTCOME", -1500);

        TransactionsLinkedList trList = new TransactionsLinkedList();
        trList.addTransaction(tr1);
        trList.addTransaction(tr2);
        trList.addTransaction(tr3);

        for (Transaction tr : trList.transactionToArray()) {
            System.out.printf("%s -> %s, %s, %d, %s\n", tr.getSender().getName(), tr.getRecipient().getName(),
                    tr.getTransferCategory(), tr.getTransferAmount(), tr.getId());
        }

        UUID id = trList.transactionToArray()[2].getId();
        System.out.println("\nRemove transaction id=" + id);
        trList.removeTransactionById(id);

        for (Transaction tr : trList.transactionToArray()) {
            System.out.printf("%s -> %s, %s, %d, %s\n", tr.getSender().getName(), tr.getRecipient().getName(),
                    tr.getTransferCategory(), tr.getTransferAmount(), tr.getId());
        }

        System.out.println("\nException: ");
        trList.removeTransactionById(UUID.randomUUID());
    }
}
