import java.util.*;

public class Node
{
    private int pos_x;
    private int pos_y;
    private int value;
    private Set<Node> edges;
    public Set<Integer> possibleValues;
    public boolean colored;

    Node(int x,int y,int v,boolean colored)
    {
        this.pos_x = x;
        this.pos_y = y;
        this.value = v;
        this.colored = colored;

        edges = new HashSet<>();
        possibleValues = new HashSet<>();
    }

    public void initPossibleValue(int dim) { for(int i=1;i<=dim;i++) { possibleValues.add(i); } }

    public void addEdge(Node edge)
    {
        if(!this.isSameNode(edge))
        {
            edges.add(edge);
            edge.possibleValues.remove((Object) this.getValue());
            this.possibleValues.remove((Object) edge.getValue());
        }
    }

    public boolean isSameNode(Node other) { return ((this.pos_x == other.getX()) && (this.pos_y == other.getY())); }

    public int getX() { return pos_x; }

    public int getY() { return pos_y; }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public boolean isColored() { return colored; }

    public void setColored(boolean colored) { this.colored = colored; }

    public Set<Node> getEdges() { return edges; }

    public int getMinDegree()
    {
        int degree=0;
        for(Node neighbor: this.edges)
        {
            if(!neighbor.isColored()) degree++;
        }
        return degree;
    }

    public String printNode() { return "Node: (" + this.getX() + ", " + this.getY() + ") value: " + this.value + "\n"; }

    public void printPossibleValue()
    {
        System.out.print("possible value: ");
        for(Integer v:this.possibleValues) { System.out.print(v + "\t"); }
        System.out.println("\n");
    }
    @Override
    public String toString() {
        String str = "";

        str += "Node: (" + this.getX() + ", " + this.getY() + ") value: " + this.value + "\n";
        str += "Edges: ";
        for(Node e: edges)
        {
            str += "( " + e.getX() +", " + e.getY() + ", " + e.value + " )\t";
        }

        str += "\n";

        return str;
    }

    public static Comparator<Node> sdfOrder = (o1, o2) -> {
        if(o1.possibleValues.size() < o2.possibleValues.size())  return -1;

        else if(o1.possibleValues.size() > o2.possibleValues.size()) return 1;

        else return 0;
    };

    public static Comparator<Node> position = (o1, o2) -> {
        if(o1.pos_x < o2.pos_x)  return -1;

        else if(o1.pos_x == o2.pos_x)
        {
            return Integer.compare(o1.pos_y, o2.pos_y);
        }

        else return 1;
    };

    public static Comparator<Node> minDegree = (o1, o2) -> {
        if(o1.getMinDegree() < o2.getMinDegree())  return -1;

        else if(o1.getMinDegree() > o2.getMinDegree()) return 1;

        else return 0;
    };

    public static Comparator<Node> brelaz = (o1, o2) -> {
        if(o1.possibleValues.size() < o2.possibleValues.size())  return -1;

        else if(o1.possibleValues.size() > o2.possibleValues.size()) return 1;

        else
        {
            if(o1.getMinDegree() < o2.getMinDegree())  return -1;

            else if(o1.getMinDegree() > o2.getMinDegree()) return 1;

            else return 0;
        }
    };
}
