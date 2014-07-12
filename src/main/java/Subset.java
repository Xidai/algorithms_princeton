import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        String[] input = StdIn.readAllStrings();
        for (String anInput : input) {
            randomizedQueue.enqueue(anInput);
        }

        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < k; i++){
            System.out.println(iterator.next());
        }
    }
}
