package ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User Mike = new User();
        User John = new User();

        Mike.setId(123);
        Mike.setName("Mike");
        Mike.setBalance(1000);

        System.out.printf("User 1: %s, id: %d, balance: %d\n", Mike.getName(), Mike.getId(), Mike.getBalance());

        John.setId(234);
        John.setName("John");
        John.setBalance(2000);

        System.out.printf("User 2: %s, id: %d, balance: %d\n", John.getName(), John.getId(), John.getBalance());

        Transaction tr = new Transaction();

        tr.setId(UUID.randomUUID());
        tr.setRecipient(Mike);
        tr.setSender(John);
        tr.setTransferCategory("OUTCOME");
        tr.setTransferAmount(-500);

        System.out.printf("%s -> %s, %s, %d, %s\n", tr.getSender().getName(), tr.getRecipient().getName(),
                tr.getTransferCategory(), tr.getTransferAmount(), tr.getId());
    }
}
