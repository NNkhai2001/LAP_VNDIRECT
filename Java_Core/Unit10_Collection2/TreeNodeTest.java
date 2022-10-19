package Unit10_Collection2;

import java.util.List;

class TreeNodeTest {
    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("0");
        TreeNode<String> node1 = new TreeNode<>("1.0");
        root.addChild(node1);
        root.addChild(new TreeNode<>("1.1"));
        root.addChild(new TreeNode<>("1.2"));
        root.addChild(new TreeNode<>("1.3"));

        TreeNode<String> node2 = node1.getNextSibling();

        //System.out.println("Next sibling of "+node1.getValue()+" is "+node2.getValue());
        TreeNode<String> node3 = node2.getNextSibling();
        TreeNode<String> node4 = node2.addChild("2.0.0");
        TreeNode<String> node5= node2.addChild("2.1.0");
        TreeNode<String> node6 = node2.addChild("2.1.1");
        //System.out.println(node4.getNextSibling().getValue());
        //System.out.println("Next sibling of "+node2.getValue()+" is "+node3.getValue());
        List<String> list = root.chonloc(value -> {
            return value.contains(".0");
        });
        System.out.println("co tong so: "+list.size()+ " chua .0");
        list.forEach(value -> System.out.println("phan tu loc ===> "+value));

    }
}