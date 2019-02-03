import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    static void printTree(String s, String st, Node tree) {
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
        Comparator<Node> comparatorString = Comparator.comparing(Node::getLetter, Comparator.comparing(x -> x.charAt(0), Character::compareTo)).reversed();//
        Comparator<Node> comparator = Comparator.comparing(Node::getCount, Long::compareTo).thenComparing(comparatorString);
        Queue<Node> q = new PriorityQueue<>(comparator);

        Scanner scanner = new Scanner(System.in);//aaaaaaaaaaaaaaabbbbbbbccccccddddddeeeee
        String s = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        long n = s.chars().distinct().count(); // количество уникальных букв
        if (n == 1) {
            System.out.print("1 1\n" + s + ": 0\n0");
        } else {
            s.chars().mapToObj(x -> (char) x)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<Character, Long>comparingByValue().thenComparing(Map.Entry::getKey))
                    .forEach(x -> {
                        sb.append(x.getKey());
                        q.add(new Node(Character.toString(x.getKey()), x.getValue()));
                    });
            String st = sb.reverse().toString();//финальный отсортированный список букв

            Node i;
            Node j;
            for (long k = n + 1; k <= 2 * n - 1; k++) {
                i = q.poll();
                j = q.poll();
                Node p = new Node(j.getLetter() + i.getLetter(), i.getCount() + j.getCount());
                p.setLeft(i);
                p.setRight(j);
                q.offer(p);
            }
            Node tree = q.poll();
            printTree(s, st, tree);
        }
    }
}