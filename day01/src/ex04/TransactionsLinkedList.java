package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node head = null;
    private Node tail = null;
    private Integer count = 0;

    @Override
    public void addTransaction(Transaction newTransaction) {
        if (head == null) {
            head = new Node(null, newTransaction, null);
        } else if (tail == null) {
            tail = new Node(head, newTransaction, null);
            head.next = tail;
        } else {
            Node newItem = new Node(tail, newTransaction, null);
            tail.next = newItem;
            tail = newItem;
        }

        count++;
    }

    @Override
    public void removeTransactionById(UUID id) {
        Node current = head;
        Node prev;
        Node next;

        if (head.item.getId().equals(id)) {
            head = head.next;
            head.prev = null;
            count--;
        } else if (tail.item.getId().equals(id)) {
            tail = tail.prev;
            tail.next = null;
            count--;
        } else {
            while (current != tail) {
                if (current.item.getId().equals(id)) {
                    prev = current.prev;
                    next = current.next;
                    prev.next = next;
                    next.prev = prev;
                    count--;
                    break;
                }

                current = current.next;
            }

            if (current == tail) {
                throw new TransactionNotFoundException("Transaction " + id + " not found");
            }
        }
    }

    @Override
    public Transaction[] transactionToArray() {
        Node current = head;

        Transaction[] array = new Transaction[count];

        current = head;
        System.out.println("here, size of array " + count);

        for (int i = 0; i < count; i++) {
            array[i] = current.item;

            if (current != tail) {
                current = current.next;
            }
        }

        return array;
    }

    @Override
    public boolean isInList(UUID id) {
        Node current = head;

        for (int i = 0; i < count; i++) {
            if (current.item.getId().equals(id)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public Transaction getTransactionById(UUID id) {
        Node current = head;

        while (current != tail) {
            if (current.item.getId().equals(id)) {
                return current.item;
            }

            current = current.next;
        }

        return null;
    }
}
