package ex03;

import java.util.LinkedList;

public class Node {
        Transaction item;
        Node next;
        Node prev;

        Node(Node prev, Transaction element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
}
