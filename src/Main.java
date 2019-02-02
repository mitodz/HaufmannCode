import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    static void printTree(String s, Node tree) {
        String st = tree.getLetter(); //финальный отсортированный список букв
        int n = st.length(); //количество уникальных букв
        System.out.println(n + " " + tree.getBits()); //реализация вывода уникальных букв и количества бит
        for (int i = 0; i < n; i++) { //реализация кода на каждую букву
            System.out.println(st.charAt(i) + ": " + tree.writeCode(Character.toString(st.charAt(i))));
            tree.clearCode();
        }
        for (int j = 0; j < s.length(); j++) {//реализация кода на весь текст
            System.out.print(tree.writeCode(Character.toString(s.charAt(j))));
            tree.clearCode();
        }
    }

    public static void main(String[] args) {
        Comparator<Node> comparatorString = Comparator.comparing(Node::getLetter, String::compareToIgnoreCase).reversed();
        Comparator<Node> comparator = Comparator.comparing(Node::getCount).thenComparing(comparatorString);
        Queue<Node> q = new PriorityQueue<>(comparator);

        Scanner scanner = new Scanner("abacabadddd");
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
            boolean isEmptyAndAlphabeticCompare = q.isEmpty() && (i.getLetter().compareToIgnoreCase(j.getLetter())>0);
            Node p = new Node(isEmptyAndAlphabeticCompare ? i.getLetter() + j.getLetter() :
                    j.getLetter() + i.getLetter(), i.getCount() + j.getCount());
            p.setLeft(isEmptyAndAlphabeticCompare ? j : i);
            p.setRight(isEmptyAndAlphabeticCompare ? i : j);
            q.add(p);
        }
        Node tree = q.poll();
        printTree(s,tree);
    }
}