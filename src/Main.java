import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    static String getFinalListOfSortedLetters (Queue<Node> q) {
        Queue<Node> qClone = new PriorityQueue<>(q);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q.size(); i++) {
            sb.append(qClone.poll().getLetter());
        }
        return sb.reverse().toString();
    }

    static void printTree(String s, String st, Node tree) {
        int n = st.length();
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

        Scanner scanner = new Scanner("abacabaddddddd");
        String s = scanner.nextLine();
        long n = s.chars().distinct().count(); // количество уникальных букв

        s.chars().mapToObj(x -> (char) x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().thenComparing(Map.Entry::getKey))
                .forEach(x -> q.add(new Node(Character.toString(x.getKey()), x.getValue())));

        String st = getFinalListOfSortedLetters(q); //финальный отсортированный список букв
        Node i;
        Node j;
        for (long k = n + 1; k <= 2 * n - 1; k++) {
            i = q.poll();
            j = q.poll();
            Node p = new Node(q.isEmpty() && (n % 2) != 0 ? i.getLetter() + j.getLetter() :
                    j.getLetter() + i.getLetter(), i.getCount() + j.getCount());
            p.setLeft(q.isEmpty() && (n % 2) != 0 ? j : i);
            p.setRight(q.isEmpty() && (n % 2) != 0 ? i : j);
            q.add(p);
        }
        Node tree = q.poll();
        printTree(s,st,tree);
    }
}