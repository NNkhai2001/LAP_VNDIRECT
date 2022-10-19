package Unit10_Collection2;

public class TestNode {
    private void test(Node<?> node) {
        while (node != null) {
            System.out.println("Value is: " + node.getValue() + "");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Node<String> root = new Node<>("Step 1");
        Node<String> node1 = new Node<>("Step 2");
        root.next = node1;
        TestNode testNode = new TestNode();
        testNode.test(root);
        System.out.println("==================");
        Node<Integer> root2 = new Node<Integer>(99
                , new Node<Integer>(23, new Node<Integer>(11)));
        testNode.test(root2);


    }
}