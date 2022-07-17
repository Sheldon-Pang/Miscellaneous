/* Implementation of Singly LinkedList */
public class MyLinkedListNode {
    int data;
    MyLinkedListNode next;
    MyLinkedListNode(int x) {
        data = x;
    }
}

class MyLinkedList {

    MyLinkedListNode head; /* Pseudo-head */
    int length; /* Store the length of the LinkedList */

    /* Initialize Head Node */
    public MyLinkedList() {
        head = new MyLinkedListNode(0);
        length = 0;
    }

    /* Get the value of the node at index-th, return -1 when invalid */
    public int get(int index) {

        if (index < 0 || index >= length)
            return -1;

        MyLinkedListNode currentNode = head;
        for (int i = 0; i <= index; i++)
            currentNode = currentNode.next;

        return currentNode.data;
    }

    /* Add to the front of the LinkedList */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /* Add to the tail of the LinkedList */
    public void addAtTail(int val) {
        addAtIndex(length, val);
    }

    /* Add the node at index-th */
    public void addAtIndex(int index, int val) {

        if (index > length || index < 0) /* can't insert */
            return;

        MyLinkedListNode currentNode = head;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;

        MyLinkedListNode newNode = new MyLinkedListNode(val);
        newNode.next = currentNode.next;
        currentNode.next = newNode;
        length++;
    }

    /* Delete the node at index-th */
    public void deleteAtIndex(int index) {

        if (index < 0 || index >= length)
            return;

        MyLinkedListNode currentNode = head;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;

        currentNode.next = currentNode.next.next;
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