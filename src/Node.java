class Node {
    private long count;
    private static long bits;
    private String letter;
    private Node left;
    private Node right;

    Node(String letter, long count) {
        this.count = count;
        this.letter = letter;
    }

    long getBits() {
        if (left!=null) {
            bits+=left.getCount();
            left.getBits();
        }
        if (right!=null) {
            bits+=right.getCount();
            right.getBits();
        }
        return bits;
    }


    long getCount() {
        return count;
    }

    String getLetter() {
        if (letter==null) {
            return "";
        }
        return letter;
    }

    Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }
}
