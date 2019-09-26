import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Graph {

    private static int time = 0;

    private enum Color {
        WHITE,
        GRAY,
        BLACK;
    }

    private class Vertex {
        private int adjacencyListIndex;
        private int key;
        private Vertex predecessor;
        private Color color;
        private int startTime;
        private int endTime;
        private int dist;
        private int breadthFirstDistance;

        public void setAdjacenceyListIndex(int adjacencyListIndex){
            this.adjacencyListIndex = adjacencyListIndex;
        }

        public int getAdjacencyListIndex(){
            return this.adjacencyListIndex;
        }

        public void setKey(int key){
            this.key = key;
        }

        public int getKey(){
            return this.key;
        }

        public void setPredecessor(Vertex predecessor){
            this.predecessor = predecessor;
        }

        public Vertex getPredecessor(){
            return this.predecessor;
        }

        public void setColor(Color color){
            this.color = color;
        }

        public Color getColor(){
            return this.color;
        }

        public void setStartTime(int startTime){
            this.startTime = startTime;
        }

        public int getStartTime(){
            return this.startTime;
        }

        public void setEndTime(int endTime){
            this.endTime = endTime;
        }

        public int getEndTime(){
            return this.endTime;
        }

        public void setDist(int dist){
            this.dist = dist;
        }

        public int getDist(){
            return this.dist;
        }

        public void setBreadthFirstDistance(int breadthFirstDistance){
            this.breadthFirstDistance = breadthFirstDistance;
        }

        public int getBreadthFirstDistance() {
            return this.breadthFirstDistance;
        }

        @Override
        public String toString(){
            return Integer.toString(this.key);
        }

        @Override
        public boolean equals(Node compare){
            return (this.hashCode() == compare.hashCode());
        }

        @Override
        public int hashCode(){
            return this.key;
        }

    }

    private class Node {
        private int index;
        private Node next;

        public Node(int index, Node next){
            this.index = index;
            this.next = next;
        }

        public void setIndex(int index){
            this.index = index;
        }

        public int getIndex(){
            return this.index;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public Node getNext(){
            return this.next;
        }
    }

    private Map<Integer, Vertex> verticesMap;
    private LinkedList[] adjacencyList; // TODO fix linked list and node usage

    public void depthFirstSearch(){
        for(Vertex v: verticesMap.values()){
            v.setColor(Color.WHITE);
            v.setPredecessor(null);
        }
        time = 0;
        for(Vertex v: verticesMap.values()){
            if(v.getColor() == Color.WHITE){
                depthFirstVisit(v);
            }
        }
    }

    private void depthFirstVisit(Vertex v){
        v.setColor(Color.GRAY);
        v.setStartTime(++time);
        LinkedList list = this.adjacencyList[v.getAdjacencyListIndex()];
        Node loop = list.poll();
        loop = list.poll();
        while(loop != null){
            Vertex u = verticesMap.get(loop.getIndex());
            if(u.getColor() == Color.WHITE){
                u.setPredecessor(v);
                depthFirstVisit(u);
            }
        }
        System.out.println(v);
        v.setColor(Color.BLACK);
        v.setEndTime(++time);
    }

    public void breadthFirstSearch(){
        Vertex source = verticesMap.get(0);
        source.setColor(Color.GRAY);
        source.setBreadthFirstDistance(0);
        source.setPredecessor(null);
        for(int i=1; i<this.adjacencyList.length;i++){
            Vertx v = verticesMap.get(i);
            v.setBreadthFirstDistance(0);
            v.setColor(Color.WHITE);
            v.setPredecessor(null);
        }
        Queue q = new LinkedList<Vertex>();
        q.add(source);
        while(!q.isEmpty()){
            Vertex loop = q.poll();
            if(loop != null){
                Node list = adjacencyList[loop.getAdjacencyListIndex()];
                list = list.next();
                while(list != null){
                    int addQIndex = list.getIndex();
                    Vertex v = verticesMap.get(addQIndex);
                    if(v.getColor() == Color.WHITE){
                        v.setColor(Color.GRAY);
                        v.setAdjacenceyListIndex(loop.getAdjacencyListIndex() + 1);
                        v.setPredecessor(loop);
                    }
                }
            }
            System.out.println(loop);
            loop.setColor(Color.BLACK);
        }
    }

    private static Graph initializeGraph(int[][] adjMatrix, int numVertices){
        Graph g = new Graph();
        Node[] list = new Node[numVertices];
        for(int i=0; i< numVertices; i++){
            list[i] = new Node(i, null);
        }
        for(int i=0; i<adjMatrix.length; i++){
            
        }
        return g;
    }

    public static void main(String args[]){
        int numVertices = 6;
        int [][] adjMatrix = {{5,4}, {5,6}, {6,8}, {6,2}, {4,5}, {8,7}, {2,8}};
        Graph g = initializeGraph(adjMatrix, numVertices);
        g.breadthFirstSearch();
    }

}