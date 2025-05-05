class Solution {
    public boolean isValidBST(TreeNode root) {
        return validateBST(root, null, null);
    }

    private boolean validateBST(TreeNode cur, Integer min, Integer max) {
        if (cur == null) {
            return true;
        }

        if ((min != null && cur.val <= min) || (max != null && cur.val >= max)) {
            return false;
        }

        return validateBST(cur.left, min, cur.val) && validateBST(cur.right, cur.val, max);
    }
}