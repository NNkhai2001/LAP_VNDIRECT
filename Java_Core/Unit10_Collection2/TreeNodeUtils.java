package Unit10_Collection2;


import java.util.List;

public class TreeNodeUtils {
    public static <T> boolean contains(TreeNode<T> node, T avalue) {
        if (node.getValue().equals(avalue)) return true;
        TreeNode<T> temp = node.getFirstChild();
        while (temp != null) {
            if (contains(temp, avalue)) return true;
            temp = temp.getNextSibling();
        }
        return false;

    }

    public interface TreeNodeFilter<T> {
        public boolean filter(T value);
    }

    static <T> void visit(TreeNode<T> node, TreeNodeFilter<T> filter, List<T> list) {
        if(filter.filter(node.getValue())) list.add(node.getValue());
        TreeNode<T> temp = node.getFirstChild();
        while (temp != null) {
            visit(temp,filter,list);
            temp = temp.getNextSibling();
        }

    }


    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("0");
        TreeNode<String> node1 = new TreeNode<>("1.0");
        root.addChild(node1);
        root.addChild(new TreeNode<>("1.1"));
        root.addChild(new TreeNode<>("1.2"));
        root.addChild(new TreeNode<>("1.3"));
        TreeNode<String> node2 = node1.getNextSibling();
        TreeNode<String> node3 = node2.getNextSibling();
        TreeNode<String> node4 = node2.addChild("2.0.0");
        TreeNode<String> node5 = node2.addChild("2.1.0");
        TreeNode<String> node6 = node2.addChild("2.1.1");
        System.out.println("check contains 2.1.1= " + TreeNodeUtils.contains(root, "2.1.1"));
    }
}