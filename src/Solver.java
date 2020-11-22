import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Solver
{
    private Graph graph;
    private int dim;
    private int totalNode;
    private List<Node> nodes;

    public Solver(Graph graph)
    {
        this.graph = graph;
        this.dim = graph.getDimension();
        totalNode = dim * dim;

        nodes = new ArrayList<>();
        for(int i=0;i<dim;i++)
        {
            for(int j=0;j<dim;j++)
            {
                Node temp = graph.getNode(i,j);
                if(temp.getValue() == 0) nodes.add(temp);
            }
        }
    }

    private boolean isSafe(Node node,int givenValue)
    {
        Set<Node> neighbors = node.getEdges();

        for(Node neighbor:neighbors)
        {
            if(neighbor.getValue() == givenValue) return false;
        }

        return true;
    }

    private boolean backTrackSolve(int index)
    {
        if(index == nodes.size()) return true;

        Node currentNode = nodes.get(index);

        for(int i=1;i<=this.dim;i++)
        {
            if(isSafe(currentNode,i))
            {
                currentNode.setValue(i);

                if(backTrackSolve(index+1)) return true;

                currentNode.setValue(0);
            }
        }

        return false;
    }

    public void printSolution()
    {
        System.out.println("\nSolution: ");
        for(int i=0;i<dim;i++)
        {
            for(int j=0;j<dim;j++)
            {
                System.out.print(graph.getNode(i,j).getValue() + "\t");
            }
            System.out.println();
        }
    }


    public boolean solve()
    {
        return backTrackSolve(0);
    }

}
