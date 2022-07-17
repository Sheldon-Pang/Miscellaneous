/* Implementation of Doubly LinkedList */
public class MyLinkedListNode {
    int data;
    MyLinkedListNode next;
    MyLinkedListNode prev;
    MyLinkedListNode(int x) {
        data = x;
    }
}

class MyLinkedList {

    int length; /* Store length of the LinkedList */
    MyLinkedListNode head, tail; /* Pesudo node head and tail */

    /* Initialize the LinkedList */
    public MyLinkedList() {
        length = 0;
        head = new MyLinkedListNode(0);
        tail = new MyLinkedListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    /* Get value of node at index-th */
    /* Time: O(n) */
    public int get(int index) {

        if (index < 0 || index >= length)
            return -1;

        /* Optimized seraching, in theory faster than O(n) */
        /* Decide search from head to tail or tail to head */
        MyLinkedListNode currentNode = head;
        if (index < length - index) {
            for (int i = 0; i <= index; i++)
                currentNode = currentNode.next;
        } else {
            currentNode = tail; /* Start search from tail */
            for (int i = 0; i < length - index; i++)
                currentNode = currentNode.prev;
        }

        return currentNode.data;
    }

    /* Insert after the head of the LinkedList */
    /* Time: O(1) */
    public void addAtHead(int val) {

        MyLinkedListNode newNode = new MyLinkedListNode(val);

        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        length++;
    }


    /* Insert at the tail of the LinkedList */
    /* Time: O(1) */
    public void addAtTail(int val) {

        MyLinkedListNode newNode = new MyLinkedListNode(val);

        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        length++;
    }

    /* Add new node before index-th */
    /* Time: O(n) */
    public void addAtIndex(int index, int val) {

        if (index > length || index < 0)
            return;

        /* Optimized seraching, in theory faster than O(n) */
        /* Decide search from head to tail or tail to head */
        MyLinkedListNode currentNode = head;
        if (index < length - index) {
            for (int i = 0; i <= index; i++)
                currentNode = currentNode.next;
        } else {
            currentNode = tail; /* Start search from tail */
            for (int i = 0; i < length - index; i++)
                currentNode = currentNode.prev;
        }

        /* Insert the node */
        MyLinkedListNode newNode = new MyLinkedListNode(val);
        newNode.next = currentNode;
        newNode.prev = currentNode.prev;
        currentNode.prev.next = newNode;
        currentNode.prev = newNode;
        length++;
    }


    /* Delete the node at index-th */
    /* Time: O(n) */
    public void deleteAtIndex(int index) {

        if (index >= length || index < 0)
            return;

        /* Optimized seraching, in theory faster than O(n) */
        /* Decide search from head to tail or tail to head */
        MyLinkedListNode currentNode = head;
        if (index < length - index) {
            for (int i = 0; i <= index; i++)
                currentNode = currentNode.next;
        } else {
            currentNode = tail; /* Start search from tail */
            for (int i = 0; i < length - index; i++)
                currentNode = currentNode.prev;
        }

        /* Delete the node */
        currentNode.next.prev = currentNode.prev;
        currentNode.prev.next = currentNode.next;
        length--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */