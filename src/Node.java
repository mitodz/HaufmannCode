public class Node {
    private long count;
    private String letter;
    private Node left;
    private Node right;

    public Node(String letter, long count) {
        this.count = count;
        this.letter = letter;
    }

    public long getBits(long bits) {
        if (left!=null) {
            bits+=left.getCount() + right.getCount();
            left.getBits(bits);
        }
        if (right!=null) {
            bits+=right.getCount() + left.getCount();
            right.getBits(bits);
        }
        return bits;
    }


    public long getCount() {
        return count;
    }

    public String getLetter() {
        if (letter==null) {
            return "";
        }
        return letter;
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
