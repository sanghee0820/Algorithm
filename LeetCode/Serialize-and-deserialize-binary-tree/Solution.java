/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Codec {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            var current = queue.poll();
            if(current == null) {
                sb.append("null,");
                continue;
            }

            sb.append(String.valueOf(current.val));
            sb.append(",");
            queue.add(current.left);
            queue.add(current.right);
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        var answer = sb.toString();
        return answer;
    }

    public TreeNode deserialize(String data) {
        var nodes = data.substring(1, data.length() - 1).split(",");
        TreeNode[] treeNodes = new TreeNode[nodes.length + 1];

        for(int index = 0; index < nodes.length; index++){
            if(nodes[index].equals("null")){
                continue;
            }

            var val = Integer.parseInt(nodes[index]);
            treeNodes[index + 1] = new TreeNode(val);
        }

        for(int left = 1, right = 2; left < treeNodes.length; left++) {
            if(treeNodes[left] == null) {
                continue;
            }

            if(right >= treeNodes.length) {
                break;
            }
            treeNodes[left].left = treeNodes[right++];
            if(right >= treeNodes.length) {
                break;
            }
            treeNodes[left].right = treeNodes[right++];
        }
    

        var root = treeNodes[1];
        return root;
    }
}