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

    public Node getNode(int x, int y) { return nodes[x][y]; }

    public int getDimension() { return this.dimension; }
}
