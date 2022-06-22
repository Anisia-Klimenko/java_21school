package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    private Node head = null;
    private Node tail = null;

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
    }

    @Override
    public void removeTransactionById(UUID id) {
        Node current = head;
        Node prev;
        Node next;

        if (head.item.getId() == id) {
            head = head.next;
            head.prev = null;
        } else if (tail.item.getId() == id) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            while (current != tail) {
                if (current.item.getId() == id) {
                    prev = current.prev;
                    next = current.next;
                    prev.next = next;
                    next.prev = prev;
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
        Integer size = 0;
        Node current = head;

        while (current != tail) {
            size++;
            current = current.next;
        }

        if (current == tail) {
            size++;
        }

        Transaction[] array = new Transaction[size];

        current = head;

        for (int i = 0; i < size; i++) {
            array[i] = current.item;

            if (current != tail) {
                current = current.next;
            }
        }

        return array;
    }
}
