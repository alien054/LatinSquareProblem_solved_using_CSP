import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node
{
    private int pos_x;
    private int pos_y;
    private int value;
    private Set<Node> edges;
    public List<Integer> possibleValues;

    Node(int x,int y,int v)
    {
        this.pos_x = x;
        this.pos_y = y;
        this.value = v;

        edges = new HashSet<>();
        possibleValues = new ArrayList<>();
    }

    public void initPossibleValue(int dim) { for(int i=1;i<=dim;i++) { possibleValues.add(i); } }

    public void addEdge(Node edge)
    {
        if(!this.isSameNode(edge))
        {
            edges.add(edge);
            edge.possibleValues.remove((Object) this.getValue());
        }
    }

    public boolean isSameNode(Node other) { return ((this.pos_x == other.getX()) && (this.pos_y == other.getY())); }

    public int getX() { return pos_x; }

    public int getY() { return pos_y; }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public Set<Node> getEdges() { return edges; }

    public int getDegree() { return this.edges.size(); }

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
}
