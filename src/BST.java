import java.util.LinkedList;
import java.util.Queue;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树插入元素E，递归算法
     *
     * @param node
     * @param e
     * @return 返回插入新节点后的二分搜索树的根
     */
    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }
        return node;
    }

    /**
     * 查询元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }


    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        } else if (e.compareTo(node.e) == 0) {
            return true;
        }
        return contains(e.compareTo(node.e) > 0 ? node.right : node.left, e);
    }

    /**
     * 二分搜索树前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树，递归算法
     *
     * @param node
     */
    private void preOrder(Node node) {
//        if (node == null) {
//            return;
//        }
        if (node != null) {
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 二分搜索书非递归前序遍历
     */
    public void preOderNR() {
//        ArrayStack<Node> stack = new ArrayStack<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            Node cur = stack.pop();
//            System.out.println(cur.e);
//
//            if (cur.right != null) {
//                stack.push(cur.right);
//            }
//            if (cur.left != null) {
//                stack.push(cur.left);
//            }
//        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }

    }

    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历以node为根的二分搜索树，递归算法
     * 排序树
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历以node为根的二分搜索树，递归算法
     * 应用：为二分搜索树释放内存
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            inOrder(node.right);
            System.out.println(node.e);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * 层序遍历 广度遍历
     */
    public void levelOrder() {

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    public int findDepth(Node node, int depth) {
        if (node == null) {
            return depth;
        }

        int rightDepth = findDepth(node.right, depth + 1);
        int leftDepth = findDepth(node.left, depth + 1);


        return rightDepth > leftDepth ? rightDepth : leftDepth;
    }

    /**
     * 寻找二分搜索树中最小元素
     *
     * @return
     */
    public E minimum() {
//        if (root == null) {
//            return null;
//        }
//        Node cur = root;
//        while (cur.left != null) {
//            cur = cur.left;
//        }
//        return cur.e;
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树中最大元素
     *
     * @return
     */
    public E maxmum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maxmum(root);
    }

    private E maxmum(Node node) {
        if (node.right == null) {
            return node.e;
        }
        return maxmum(node.right);
    }

    /**
     * 删除最小元素
     *
     * @return e
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大元素
     *
     * @return e
     */
    public E removeMax() {
        E ret = maxmum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除元素
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.right = node.left = null;

            return successor;
        }
    }

    /**
     * 求比元素小的最大值
     *
     * @param e
     * @return
     */
    public E floor(E e) {
        if (isEmpty() || e.compareTo(minimum()) < 0) {
            return null;
        }
        return floor(root, e).e;
    }

    private Node floor(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (node.e.compareTo(e) == 0) {
            return node;
        }

        if (e.compareTo(node.e) < 0) {
            return floor(node.left, e);
        }

        Node tempNode = floor(node.right, e);
        if (tempNode != null) {
            return tempNode;
        }

        return node;
    }

    /**
     * 求比元素大的最小值
     *
     * @param e
     * @return
     */
    public E ceil(E e) {
        if (isEmpty() || e.compareTo(maxmum()) > 0) {
            return null;
        }
        return ceil(root, e).e;
    }

    private Node ceil(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) == 0) {
            return node;
        }

        if (e.compareTo(node.e) > 0) {
            return ceil(node.right, e);
        }

        Node tempNode = ceil(node.left, e);
        if (tempNode != null) {
            return tempNode;
        }

        return node;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {41, 22, 58, 16, 13, 12, 33, 37, 50, 63, 42, 53};
        for (int num : nums) {
            bst.add(num);
        }
//                        41
//                   /         \
//                  22          58
//                 /  \        /  \
//                16   33     50   63
//               /      \     / \
//              13      37   42  53
//              /
//             12
//        System.out.println(bst.root.e);
//        System.out.println(bst.root.left.e);
//        System.out.println(bst.root.left.left.e);
//        System.out.println(bst.root.right.e);
//        System.out.println(bst.root.right.left.e);
//        System.out.println(bst.root.right.right.e);
//        System.out.println(bst.getSize());
//        System.out.println(bst.contains(9));
//        bst.preOrder();
//        System.out.println();
//        System.out.println(bst);
//        bst.inOrder();
//        bst.postOrder();
//        bst.preOderNR();
//        bst.levelOrder();
//        System.out.println(bst.findDepth(bst.root, 0));
//        System.out.println(bst.minimum());
//        System.out.println(bst.maxmum());
//        bst.removeMax(bst.root);
//        bst.remove(10);
//        System.out.println(bst);
        System.out.println(bst.floor(11));
        System.out.println(bst.ceil(11));
    }
}
