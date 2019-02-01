import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    static String writeCode(Node t, String letter, StringBuilder sb) {
        if (t.getLeft() == null && t.getRight() == null) return sb.toString();
        if (t.getLeft().getLetter().contains(letter)) {
            sb.append(1);
            writeCode(t.getLeft(), letter, sb);
        }
        if (t.getRight().getLetter().contains(letter)) {
            sb.append(0);
            writeCode(t.getRight(), letter, sb);
        }
        return sb.toString();
    }

    static void printTree(String s, Node tree) {
        String st = tree.getLetter();
        int n = st.length();
        System.out.println(n + " " + tree.getBits()); //реализация вывода уникальных букв и количества бит
        for (int i = 0; i < n; i++) { //реализация кода на каждую букву
            StringBuilder sb = new StringBuilder();
            System.out.println(st.charAt(i) + ": " + writeCode(tree, Character.toString(st.charAt(i)), sb));
        }
        for (int j = 0; j < s.length(); j++) {//реализация кода на весь текст
            StringBuilder sb = new StringBuilder();
            System.out.print(writeCode(tree, Character.toString(s.charAt(j)), sb));
        }
    }

    public static void main(String[] args) {
        Comparator<Node> comparatorString = Comparator.comparing(Node::getLetter, String::compareToIgnoreCase).reversed();
        Comparator<Node> comparator = Comparator.comparing(Node::getCount).thenComparing(comparatorString);
        Queue<Node> q = new PriorityQueue<>(comparator);

        Scanner scanner = new Scanner("abacabadddddd");
        String s = scanner.nextLine();
        long n = s.chars().distinct().count(); // количество уникальных букв

        s.chars().mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().thenComparing(Map.Entry::getKey))
                .forEach(x -> q.add(new Node(Character.toString(x.getKey()), x.getValue())));
        Node i;
        Node j;
        for (long k = n + 1; k <= 2 * n - 1; k++) {
            i = q.poll();
            j = q.poll();
            Node p = new Node(q.isEmpty() && n % 2 != 0 ? i.getLetter() + j.getLetter() :
                    j.getLetter() + i.getLetter(), i.getCount() + j.getCount());
            p.setLeft(q.isEmpty() && n % 2 != 0 ? j : i);
            p.setRight(q.isEmpty() && n % 2 != 0 ? i : j);
            q.add(p);
        }
        Node tree = q.poll();
        printTree(s,tree);
    }
}