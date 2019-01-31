public class Node {
    private long count;
    private String letter;
    private Node left;
    private Node right;

    public Node(String letter, long count) {
        this.count = count;
        this.letter = letter;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getLetter() {
        if (letter==null) {
            return "";
        }
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
