import java.util.*;

@SuppressWarnings("DuplicatedCode")
public class Solver
{
    private Graph graph;
    private int dim;
    private int totalNode;
    private List<Node> nodes;
    public int numOfBT;
    public int numOfBT2;
    public int numOfNodes;
    private Comparator<Node> order;

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
//                System.out.print(temp.printNode());
//                temp.printPossibleValue();
                if(temp.getValue() == 0)
                {
                    nodes.add(temp);
                }
            }
        }

        numOfBT = 0;
        numOfNodes = 0;
        numOfBT2 = 0;
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

    private boolean forwardCheck(Node node,int giveValue)
    {
        for(Node neighbor: node.getEdges())
        {
            if(neighbor.possibleValues.size() == 1 && neighbor.possibleValues.contains(giveValue))
                return false;
        }

        return true;
    }


    private boolean backTrackSolve(int index)
    {
        numOfNodes++;

        if(index == nodes.size()) return true;

        Node currentNode = nodes.get(index);

        for(int i=1;i<=this.dim;i++)
        {
            if(isSafe(currentNode,i))
            {
                currentNode.setValue(i);

                if(backTrackSolve(index+1)) return true;

                numOfBT++;
                currentNode.setValue(0);
            }
        }

//        numOfBT2++; //(this is the same measurement as numOfBT)
        return false;
    }

    private boolean backTrackSolveFC(int index)
    {
        numOfNodes++;

        if(index == nodes.size()) return true;

        Node currentNode = nodes.get(index);

        for(int i=1;i<=this.dim;i++)
        {
            if(currentNode.possibleValues.contains(i) && isSafe(currentNode,i) && forwardCheck(currentNode,i))
            {
                currentNode.setValue(i);

                for(Node neighbor: currentNode.getEdges()) { neighbor.possibleValues.remove((Object) i); }

                if(backTrackSolveFC(index+1)) return true;

                numOfBT++;

                currentNode.setValue(0);

                for(Node neighbor: currentNode.getEdges()) { neighbor.possibleValues.add(i); }
            }
        }

        numOfBT2++; //(this is the same measurement as numOfBT)
        return false;
    }

    private boolean backTrackSolveFC2()
    {
        numOfNodes++;
        if(nodes.isEmpty()) return true;

        nodes.sort(order);
        Node currentNode = nodes.get(0);


        for(int i=1;i<=this.dim;i++)
        {
            if(currentNode.possibleValues.contains(i) && isSafe(currentNode,i) && forwardCheck(currentNode,i))
            {
                currentNode.setValue(i);
                currentNode.setColored(true);
                nodes.remove(currentNode);
                for(Node neighbor: currentNode.getEdges())
                {
                    if(!neighbor.isColored())
                        neighbor.possibleValues.remove((Object) i);
                }

                if(backTrackSolveFC2()) return true;
                numOfBT++;

                currentNode.setValue(0);
                currentNode.setColored(false);
                nodes.add(0,currentNode);
                for(Node neighbor: currentNode.getEdges())
                {
                    if(!neighbor.isColored())
                        neighbor.possibleValues.add(i);
                }

            }
        }

        numOfBT2++; //(this is the same measurement as numOfBT)
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
        System.out.println("Number of Nodes: " + numOfNodes);
        System.out.println("Number of Backtrack: " + numOfBT);
        System.out.println("Number of Backtrack2: " + numOfBT2);

    }

    private void clearSolution()
    {
        numOfBT2 = 0;
        numOfNodes = 0;
    }


    public void solve()
    {
//        backTrackSolve(0);
//        printSolution();
//        clearSolution();
//        backTrackSolveFC(0);
//        printSolution();
    }

    public void solve(Comparator<Node> order)
    {
//        backTrackSolve(0);
//        printSolution();
//        clearSolution();
        this.order = order;
//        nodes.sort(order);
//        backTrackSolveFC(0);
        backTrackSolveFC2();
        printSolution();
    }

}
