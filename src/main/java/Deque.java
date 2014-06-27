import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    public Deque() {
        size = 0;
        first = last = null;
    }                          // construct an empty deque
    public boolean isEmpty(){
        return size == 0;
    }                 // is the deque empty?
    public int size()  {
        return size;
    }                      // return the number of items on the deque
    public void addFirst(Item item){
        checkNull(item);
        Node oldNode = first;
        first = new Node(item);
        first.next = oldNode;
        if (isEmpty()){
            last = first;
        } else {
            oldNode.prev = first;
        }
        size++;
    }          // insert the item at the front

    public void addLast(Item item){
        checkNull(item);
        Node oldNode = last;
        last = new Node(item);
        last.prev = oldNode;
        if (isEmpty()){
            first = last;
        } else {
            oldNode.next = last;
        }
        size++;
    }           // insert the item at the end
    public Item removeFirst(){
        checkEmpty();
        Node target = first;
        first = first.next;
        size--;
        return target.item;
    }                // delete and return the item at the front

    public Item removeLast(){
        checkEmpty();
        Node target = last;
        last = last.prev;
        size--;
        return target.item;
    }                 // delete and return the item at the end
    public Iterator<Item> iterator(){
        //TODO when calls remove() and calls next()
        return null;
    }         // return an iterator over items in order from front to end

    private void checkEmpty() {
        if (isEmpty()){
            throw new UnsupportedOperationException();
        }
    }

    private void checkNull(Item item) {
        if (item == null){
            throw new NullPointerException();
        }
    }

    private class Node{
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    public static void main(String[] args){
        Deque<String> deque = new Deque<String>();
        deque.addLast("a");
        System.out.println("add a to last");
        deque.addLast("b");
        System.out.println("add b to last");
        System.out.println("size is: " + deque.size());

        System.out.println("remove first item: " + deque.removeFirst());
        System.out.println("remove first item: " + deque.removeFirst());

        System.out.println("size is: " + deque.size() + ", and size should be exactly 0");
    }   // unit testing
}
