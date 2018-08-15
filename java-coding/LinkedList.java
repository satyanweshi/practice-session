
public class LinkedList {
    private static class Node {
        private int key;
        private Node next;

        Node(int key){
            this.key = key;
            this.next = null;
        }

        public int getKey(){
            return this.key;
        }

        public void setKey(int key){
            this.key = key;
        }

        public Node getNext(){
            return this.next;
        }

        public void setNext(Node next){
            this.next = next;
        }
    }

    private Node head;

    private void swapNodes(int x, int y){
        // find prevX, nodeX, nextX
        Node prevX = null;
        Node nodeX = null, nextX = null ;
        Node loop = this.head;
        while(loop != null){
            if(loop.getKey() == x){
                nodeX = loop;
                nextX = loop.getNext();
                break;
            }
            prevX = loop;
            loop = loop.getNext();
        }
        if(nodeX == null){
            System.out.println("Cannot find " + x + " in Linked List");
            return;
        }

        // find prevY, nodeY, nextY
        Node prevY = null;
        Node nodeY = null, nextY = null;
        loop = this.head;
        while(loop != null){
            if(loop.getKey() == y){
                nodeY = loop;
                nextY = loop.getNext();
                break;
            }
            prevY = loop;
            loop = loop.getNext();
        }
        if(nodeY == null){
            System.out.println("Cannot find " + x + " in Linked List");
            return;
        }

        //swap
        if(prevX != null){
            prevX.setNext(nodeY);
        }
        else{
            this.head = nodeY;
        }
        nodeY.setNext(nextX);
        if(prevY != null){
            prevY.setNext(nodeX);
        }
        else {
            this.head = nodeX;
        }
        nodeX.setNext(nextY);
    }

    public void setHead(Node head){
        this.head = head;
    }

    public Node getHead() {
        return this.head;
    }

    public void printLinkedList(){
        Node loop = head;
        while(loop != null){
            System.out.print(loop.getKey() + ", ");
            loop = loop.getNext();
        }
        System.out.println();
    }

    public void append(Node newNode){
        if(newNode == null) return;

        Node start = this.head;
        if(start == null) {
            this.head = newNode;
            return;
        }
        else{
            while(start.getNext() != null){
                start = start.getNext();
            }
            start.setNext(newNode);
        }
    }

    // assumes the Linked List is sorted in ascending order
    public static void intersection(LinkedList first, LinkedList second){
        Node fNode = first.getHead();
        Node sNode = second.getHead();
        LinkedList intersection = new LinkedList();
        while(fNode != null && sNode != null){
            if(fNode.getKey() == sNode.getKey()){
                intersection.append(new Node(fNode.getKey()));
                fNode = fNode.getNext();
                sNode = sNode.getNext();
            }
            else if(fNode.getKey() > sNode.getKey()){
                sNode = sNode.getNext();
            }
            else if(fNode.getKey() < sNode.getKey()){
                fNode = fNode.getNext();
            }
        }
        intersection.printLinkedList();
    }

    public static void runSwapNodeTest(){
        LinkedList linkList = new LinkedList();
        Node next = null;
        Node latest = null;
        for(int i=0;i<10;i++){
            latest = new Node(i);
            latest.setNext(next);
            next = latest;
        }
        linkList.setHead(latest);
        linkList.printLinkedList();
        linkList.swapNodes(9,3);
        linkList.printLinkedList();
    }

    public static void runNodeIntersectionTest(){
        LinkedList first = new LinkedList();
        LinkedList second = new LinkedList();

        first.append(new Node(1));
        first.append(new Node(2));
        first.append(new Node(3));
        first.append(new Node(4));
        first.append(new Node(5));
        
        second.append(new Node(2));
        second.append(new Node(4));
        second.append(new Node(5));
        second.append(new Node(6));

        intersection(first, second);
    }

    public static void main(String args[]){
        //runSwapNodeTest();

        runNodeIntersectionTest();
    }
}