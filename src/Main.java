import java.util.ArrayList;

public class Main {

    private static double testSet(Set<String> set, String fileName) {

        long startTime = System.nanoTime();

        System.out.println(fileName);

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(fileName, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total different words: " + set.getSize());
        }


        long endtTime = System.nanoTime();

        return (endtTime - startTime) / 1000000000.0;

    }

    public static void main(String[] args) {

        String fileName = "pride-and-prejudice.txt";

        BSTSet<String> bstSet = new BSTSet<>();

        double time1 = testSet(bstSet, fileName);

        System.out.println("bst time1: " + time1);
        System.out.println();
        LinkedListSet<String> linkedListSet = new LinkedListSet<>();

        double time2 = testSet(linkedListSet, fileName);

        System.out.println("link time2: " + time2);
    }
}
