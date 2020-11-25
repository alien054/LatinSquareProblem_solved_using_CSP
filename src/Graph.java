import java.util.Set;

public class Graph
{
    private int dimension;
    public Node[][] nodes;

    Graph(int m)
    {
        this.dimension = m;

        nodes = new Node[m][m];
    }

    public void addNode(int x,int y,int v)
    {
        Node temp = new Node(x,y,v);
        if(v == 0) temp.initPossibleValue(dimension);
        else temp.possibleValues.add(v);
        nodes[x][y] = temp;
    }


    public void addEdge(Node node1, Node node2)
    {
        node1.addEdge(node2);
        node2.addEdge(node1);
    }

    public void constructGraph()
    {
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                Node current = this.nodes[i][j];

                //Add edge for all nodes in same row
                for(int k=0;k<dimension;k++)
                {
                    this.addEdge(current,this.nodes[current.getX()][k]);
                }

                //Add edge for all nodes in same column
                for(int k=0;k<dimension;k++) this.addEdge(current,this.nodes[k][current.getY()]);
            }
        }
    }

    public Set<Node> getNeighbors(int x,int y) { return getNode(x,y).getEdges(); }

    public boolean hasEdge(int x1,int y1,int x2,int y2)
    {
        Node node1 = getNode(x1,y1);
        Node node2 = getNode(x2,y2);

        return (node1.getEdges().contains(node2) || node2.getEdges().contains(node1));
    }

    public Node getNode(int x, int y) { return nodes[x][y]; }

    public int getDimension() { return this.dimension; }


    @Override
    public String toString() {
        String str = "";
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                str += nodes[i][j];
            }
        }

        str += "\n";

        return str;
    }
}
