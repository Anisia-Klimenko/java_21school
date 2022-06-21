package ex03;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private String transferCategory;
    private int transferAmount;

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public String getTransferCategory() {
        return transferCategory;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransferCategory(String transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setTransferAmount(int transferAmount) {
        if ((transferCategory.equals("INCOME") && transferAmount > 0) ||
                (transferCategory.equals("OUTCOME") && transferAmount < 0)) {
            this.transferAmount = transferAmount;
        }
    }
}
