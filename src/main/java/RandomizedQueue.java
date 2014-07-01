import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    }                // is the queue empty?

    public int size() {
        return size;
    }                   // return the number of items on the queue

    public void enqueue(Item item) {
        checkNull(item);
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }           // add the item

    private void resize(int newSize) {
        Item[] copy = items;
        items = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++){
            items[i] = copy[i];
        }
    }

    public Item dequeue() {
        checkEmpty();
        int randomIndex = StdRandom.uniform(size);
        if (isQuarterFull()) {
            resize(items.length / 2);
        }
        Item returnItem = items[randomIndex];
            removeItem(items, randomIndex, size);
        size--;
        return returnItem;
    }                    // delete and return a random item

    private void removeItem(Item[] array, int index, int itemSize) {
        int i;
        for (i = index; i < itemSize - 1; i++){
            array[i] = array[i + 1];
        }
        array[i] = null;
    }

    public Item sample() {
        checkEmpty();
        int i = StdRandom.uniform(size);
        if (isQuarterFull()) {
            resize(items.length / 2);
        }
        return items[i];
    }                     // return (but do not delete) a random item

    private boolean isQuarterFull() {
        return size > 0 && size == items.length / 4;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }         // return an independent iterator over items in random order

    private void checkNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Integer[] indexNotVisited;
        int itemSize;

        private RandomizedQueueIterator() {
            this.indexNotVisited = new Integer[size];
            itemSize = size;
            for (int i = 0; i < size; i++){
                indexNotVisited[i] = i; //each item is NOT visited.
            }
        }

        @Override
        public boolean hasNext() {
            return itemSize > 0;
        }

        @Override
        public Item next() {
            checkEmpty();
            int random = StdRandom.uniform(itemSize);
            int randomIndex = indexNotVisited[random];
            Item returnItem = items[randomIndex];
            removeItem(indexNotVisited, random, itemSize);
            itemSize--;
            return returnItem;
        }

        private void removeItem(Integer[] array, int index, int itemSize) {
            int i;
            for (i = index; i < itemSize - 1; i++){
                array[i] = array[i + 1];
            }
            array[i] = null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("a");
        randomizedQueue.enqueue("b");
        randomizedQueue.enqueue("c");
        randomizedQueue.enqueue("d");
        randomizedQueue.enqueue("e");
        randomizedQueue.enqueue("f");
        System.out.println("-----------start iterating");
        for (String s : randomizedQueue) {
            System.out.println(s);
        }
        System.out.println("-----------end iterating");
        System.out.println("size is: " + randomizedQueue.size());
        System.out.println("sample item: " + randomizedQueue.sample());
        System.out.println("remove item: " + randomizedQueue.dequeue());
        System.out.println("remove item: " + randomizedQueue.dequeue());
        System.out.println("remove item: " + randomizedQueue.dequeue());
        System.out.println("remove item: " + randomizedQueue.dequeue());
        System.out.println("remove item: " + randomizedQueue.dequeue());
        System.out.println("remove item: " + randomizedQueue.dequeue());
        System.out.println("size is: " + randomizedQueue.size());
    }   // unit testing
}