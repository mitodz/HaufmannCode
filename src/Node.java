class Node {
    private long count;
    private String letter;
    private Node left;
    private Node right;

    Node(String letter, long count) {
        this.count = count;
        this.letter = letter;
    }

    long getBits(long bits) {
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
