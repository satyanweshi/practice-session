

class BinarySearchTree {
    private class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        Node(int value, Node parent, Node left, Node right){
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public int getValue(){
            return this.value;
        }

        public void setValue(int value){
            this.value = value;
        }

        public Node getParent(){
            return this.parent;
        }

        public void setParent(Node parent){
            this.parent = parent;
        }

        public Node getLeft(){
            return this.left;
        }

        public void setLeft(Node left){
            this.left = left;
        }

        public Node getRight(){
            return this.right;
        }

        public void setRight(Node right){
            this.right = right;
        }

        public String toString() {
            return Integer.toString(this.value);
        }
    }

    private Node root;

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node root){
        this.root = root;
    }

    BinarySearchTree(Node root){
        this.root = root;
    }

    BinarySearchTree(){
        this.root = null;
    }

    public String inOrderTreeWalk(Node node){
        if(node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(inOrderTreeWalk(node.getLeft()));
        sb.append(node + ", ");
        sb.append(inOrderTreeWalk(node.getRight()));
        return sb.toString();
    }

    public void postOrderTreeWalk(){

    }

    public void preOrderTreeWalk(){

    }

    public Node treeMinimum(Node node){
        if(node == null) return null;
        if(node.getLeft() == null) return node;

        Node temp = node.getLeft();
        while(temp != null){
            if(temp.getLeft() == null) return temp;
            temp = temp.getLeft();
        }
        return null;
    }

    public Node treeMaximum(Node node){
        if(node == null) return null;
        if(node.getRight() == null) return node;

        Node temp = node.getRight();
        while(temp != null){
            if(temp.getRight() == null) return temp;
            temp = temp.getRight();
        }
        return null;
    }

    public Node bstSearch(int value){
        Node node = this.root;
        while(node != null){
            if(node.getValue() == value) return node;
            if(node.getValue() > value) {
                node = node.getLeft();
            }
            else{
                node = node.getRight();
            }
        }
        return null;
    }

    public Node treeSuccessor(int value){
        Node node = bstSearch(value);
        return treeSuccessor(node);
    }

    public Node treeSuccessor(Node node){
        if(node == null) return null;
        if(node.getRight() == null) {
            Node x = node;
            Node y = node.getParent();
            while( y != null && y.getRight() == x){
                x = y;
                y = y.getParent();
            }
            return y;
        }
        else{
            return treeMinimum(node.getRight());
        }
    }

    public Node treePredecessor(int value){
        Node node = bstSearch(value);
        if(node == null) System.out.println("No such value");
        if(node.getLeft() == null) {
            Node x = node;
            Node y = node.getParent();
            while( y != null && y.getLeft() == x){
                x = y;
                y = y.getParent();
            }
            return y;
        }
        else{
            return treeMaximum(node.getLeft());
        }
    }

    public void insertNode(int value){
        if(this.root == null){
            this.root = new Node(value, null, null, null);
            return;
        }
        Node node = this.root;
        Node parent = this.root;
        while(node != null ){
            parent = node;
            if(node.getValue() >=  value){
                node = node.getLeft();
            }
            else{
                node = node.getRight();
            }
        }
        if(parent.getValue() >= value){
            parent.setLeft(new Node(value, parent, null, null));
        }
        else{
            parent.setRight(new Node(value, parent, null, null));
        }
    }

    public void deleteNode(int value){
        Node node = bstSearch(value);
        if(node == null) System.out.println("No node to delete");

        Node x = null;
        Node y = null;
        if(node.getLeft() == null || node.getRight() == null){
            y = node;
        }
        else{
            y = treeSuccessor(node);
        }
        System.out.println(y);

        if(y.getLeft() != null){
            x = y.getLeft();
        }
        else{
            x = y.getRight();
        }
        if(x != null){
            x.setParent(y.getParent());
        }
        if(y.getParent() == null){
            this.root = x;
        }
        else{
            if(y.getParent().getLeft() == y){
                y.getParent().setLeft(x);
            }
            else{
                y.getParent().setRight(x);
            }
        }
        if( y != node){
            node.setValue(y.getValue());
        }
    }

    public String toString(){
        return inOrderTreeWalk(this.root);
    }

    private static void createDummyBST(BinarySearchTree tree){
        tree.insertNode(10);
        tree.insertNode(11);
        tree.insertNode(9);
        tree.insertNode(54);
        tree.insertNode(21);
        tree.insertNode(34);
        tree.insertNode(78);
        tree.insertNode(56);
        tree.insertNode(23);
        tree.insertNode(57);
    }

    public static void main(String args[]){
        // create BST, will also test insert node
        BinarySearchTree tree = new BinarySearchTree();
        createDummyBST(tree);

        // print In Order Walk
        System.out.println(tree);

        // print Pre Order Walk

        // print Post Order Walk

        // do a Binary Search Tree search

        // find the successor
        System.out.println(tree.treeSuccessor(34));

        // find the predecessor
        System.out.println(tree.treePredecessor(21));

        // delete a node
        tree.deleteNode(10);
        System.out.println(tree);
    }
}