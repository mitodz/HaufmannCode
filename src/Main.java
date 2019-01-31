import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static String writeCode(Node t, String letter, StringBuilder sb){

        if (t.getLeft()==null && t.getRight()==null) return sb.toString();
        if (t.getLeft().getLetter().contains(letter)) {
            sb.append(1);
            writeCode(t.getLeft(),letter,sb);
        }
        if (t.getRight().getLetter().contains(letter)) {
            sb.append(0);
            writeCode(t.getRight(),letter,sb);
        }
        return sb.toString();
    }

    public static Long getMaxChildCount(Node t) {
        if (t.getLeft().getCount()>t.getRight().getCount()) {
            return t.getLeft().getCount();
        } else return t.getRight().getCount();
    }

    public static void printTree (Node tree) {

        String s = tree.getLetter();
        int n = s.length();
        System.out.println(n + " " + tree.getCount());
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            System.out.println(s.charAt(i) + ": " + writeCode(tree,Character.toString(s.charAt(i)),sb));
            //реализовать распечатку всего текста ввиде кода Хаффмана
        }
    }

    public static void main(String[] args) {
        Comparator<Node> comparatorString = Comparator.comparing(Node::getLetter,String::compareToIgnoreCase);
        Comparator<Node> comparator = Comparator.comparing(Node::getCount).thenComparing(comparatorString.reversed());
        Queue<Node> q = new PriorityQueue<>(comparator);

        Scanner scanner = new Scanner("aaaaaaaaaaaaaaabbbbbbbccccccddddddeeeeeffffffffff");
        String s = scanner.nextLine();
        long n = s.chars().distinct().count(); // количество уникальных букв

        s.chars().mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().thenComparing(Map.Entry::getKey))
                .forEach(x -> q.add(new Node(Character.toString(x.getKey()),x.getValue())));
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
        printTree(tree);
    }
}