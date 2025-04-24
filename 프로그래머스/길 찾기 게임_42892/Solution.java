import java.util.*;

class Solution {

    static class Node {
        int x;
        int y;
        int number;
        Node left;
        Node right;

        Node(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodeList = new ArrayList<>();

        for (int index = 0; index < nodeinfo.length; index++) {
            nodeList.add(new Node(nodeinfo[index][0], nodeinfo[index][1], index + 1));
        }

        nodeList.sort((a, b) -> {
            if (b.y == a.y) {
                return a.x - b.x;
            }
            return b.y - a.y;
        });

        Node root = nodeList.get(0);
        for (int index = 1; index < nodeinfo.length; index++) {
            insertNode(root, nodeList.get(index));
        }

        List<Integer> preorderList = preorderWithStack(root);
        List<Integer> postorderList = postorderWithStack(root);

        int[][] answer = new int[2][nodeinfo.length];
        for (int index = 0; index < nodeinfo.length; index++) {
            answer[0][index] = preorderList.get(index);
            answer[1][index] = postorderList.get(index);
        }
        return answer;
    }

    private void insertNode(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
                return;
            }
            insertNode(parent.left, child);
            return;
        }
        if (parent.right == null) {
            parent.right = child;
            return;
        }
        insertNode(parent.right, child);
    }

    private List<Integer> preorderWithStack(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.number);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return result;
    }

    private List<Integer> postorderWithStack(Node root) {
        List<Integer> result = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(0, node.number);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return result;
    }
}