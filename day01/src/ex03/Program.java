package ex03;

import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        Transaction tr1 = new Transaction();
        Transaction tr2 = new Transaction();
        Transaction tr3 = new Transaction();

        User Mike = new User();
        Mike.setName("Mike");
        Mike.setBalance(1000);

        User John = new User();
        John.setName("John");
        John.setBalance(2000);

        tr1.setId(UUID.randomUUID());
        tr1.setRecipient(Mike);
        tr1.setSender(John);
        tr1.setTransferCategory("INCOME");
        tr1.setTransferAmount(200);

        tr2.setId(UUID.randomUUID());
        tr2.setRecipient(Mike);
        tr2.setSender(John);
        tr2.setTransferCategory("OUTCOME");
        tr2.setTransferAmount(-500);

        tr3.setId(UUID.randomUUID());
        tr3.setRecipient(John);
        tr3.setSender(Mike);
        tr3.setTransferCategory("OUTCOME");
        tr3.setTransferAmount(-1500);

        TransactionsLinkedList trList = new TransactionsLinkedList();
        trList.addTransaction(tr1);
        trList.addTransaction(tr2);
        trList.addTransaction(tr3);

        System.out.println(trList.transactionToArray()[0].getTransferAmount());
        System.out.println(trList.transactionToArray()[1].getTransferAmount());
        System.out.println(trList.transactionToArray()[2].getTransferAmount());

        UUID id = trList.transactionToArray()[2].getId();

        trList.removeTransactionById(id);

        System.out.println(trList.transactionToArray()[0].getTransferAmount());
        System.out.println(trList.transactionToArray()[1].getTransferAmount());
    }
}
