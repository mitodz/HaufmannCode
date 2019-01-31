import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void writeLetter (Node t){
        if (t.getLeft()!=null) {
            System.out.println(t.getLeft().getLetter());
            writeLetter(t.getLeft());
        }
        if (t.getRight()!=null) {
            System.out.println(t.getRight().getLetter());
            writeLetter(t.getRight());
        }
    }

    public static void writeCount(Node t){
        if (t.getLeft()!=null) {
            System.out.println(t.getLeft().getCount());
            writeCount(t.getLeft());
        }
        if (t.getRight()!=null) {
            System.out.println(t.getRight().getCount());
            writeCount(t.getRight());
        }
    }

    public static String writeCode(Node t, String letter){
        StringBuilder sb = new StringBuilder();
        if (t.getLeft()==null && t.getRight()==null) return sb.toString();
        if (t.getLeft().getLetter().contains(letter)) {
            sb.append(1);
            writeCode(t.getLeft(),letter);
        }
        if (t.getRight().getLetter().contains(letter)) {
            sb.append(0);
            writeCode(t.getRight(),letter);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Comparator<Node> comparatorString = Comparator.comparing(Node::getLetter,String::compareToIgnoreCase);
        Comparator<Node> comparator = Comparator.comparing(Node::getCount).thenComparing(comparatorString.reversed());
        Queue<Node> q = new PriorityQueue<>(comparator);

        Scanner scanner = new Scanner("aaaaaaaaaaaaaaabbbbbbbccccccddddddeeeee");
        String s = scanner.nextLine();
        long n = s.chars().distinct().count(); // количество уникальных букв

        s.chars().mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().thenComparing(Map.Entry::getKey))
                .forEach(x -> q.add(new Node(Character.toString(x.getKey()),x.getValue())));

//        Node m;
//        while (!q.isEmpty()) {
//            m=q.poll();
//            System.out.println(m.getLetter() + ": " + m.getCount());
//        }
//

        Node i;
        Node j;
        for (long k = n + 1; k <= 2 * n - 1; k++) {
                i=q.poll();
                j=q.poll();
                Node p = new Node(q.isEmpty() ? i.getLetter() + j.getLetter() : j.getLetter() + i.getLetter(),i.getCount() + j.getCount());
                p.setLeft(q.isEmpty() ? j : i);
                p.setRight(q.isEmpty() ? i : j);
                q.add(p);
        }

        Node tree = q.poll();
//        System.out.println(tree.getLetter());
//        writeLetter(tree);
        System.out.println(writeCode(tree,"b"));
    }

}