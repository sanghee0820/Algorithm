class Solution {
    public Node copyRandomList(Node head) {
        List<Node> copiedNodes = new ArrayList<>();
        Map<Node, Integer> indexMap = new HashMap<>();
        Node cur = head;
        int index = 0;
        while(cur != null) {
            Node copiedNode = new Node(cur.val);
            indexMap.put(cur, index++);
            copiedNodes.add(copiedNode);
            cur = cur.next;
            if(copiedNodes.size() != 1) {
                copiedNodes.get(copiedNodes.size() - 2).next = copiedNode;
            }
        }

        cur = head;
        index = 0;
        while(cur != null) {
            if(cur.random != null) {
                int randomIndex = indexMap.get(cur.random);
                copiedNodes.get(index).random = copiedNodes.get(randomIndex);
            }
            index++;
            cur = cur.next;
        }
        return copiedNodes.size() == 0 ? null : copiedNodes.get(0);
    }
}