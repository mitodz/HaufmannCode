class Node {
    private long count;
    private static long bits;
    private static int childs;
    private String letter;
    private static StringBuilder code = new StringBuilder();
    private Node left;
    private Node right;

    Node(String letter, long count) {
        this.count = count;
        this.letter = letter;
    }

    int getChildsCount () {
        if (left!=null) {
            childs++;
            left.getChildsCount();
        }
        if (right!=null) {
            childs++;
            right.getChildsCount();
        }
        return childs;
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

    String writeCode(String letter) {
        if (left == null && right == null) return code.toString();
        if (left.getLetter().contains(letter)) {
            code.append(1);
            left.writeCode(letter);
        }
        if (right.getLetter().contains(letter)) {
            code.append(0);
            right.writeCode(letter);
        }
        return code.toString();
    }

    void clearCode () {
        code.setLength(0);
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
