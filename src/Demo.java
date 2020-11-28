import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Comparator;

public class Demo
{
    static String[] dataset = {"alien.txt","d-10-01.txt","d-10-06.txt","d-10-07.txt","d-10-08.txt","d-10-09.txt","d-15-01.txt"};
    static Graph graph;

    public static void DataLoader(int datasetIndex, Comparator<Node>order)
    {
        try {
            File file = new File(dataset[datasetIndex]);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            str = br.readLine();
            str = str.replace(";","");
            String[] s = str.split("=");
            int dim = Integer.parseInt(s[1]);
            System.out.println("Give Problem: ");
            System.out.println("dim: " + dim);
            graph = new Graph(dim);

            br.readLine();
            br.readLine();

            for(int i=0;i<dim;i++)
            {
                str = br.readLine().replace(";","");
                String[] data = str.split(",");
                for(int j=0;j<dim;j++)
                {
                    data[j] = data[j].replace("|","").replace("]","").strip();
                    System.out.print( data[j] + "\t");

                    int value = Integer.parseInt(data[j]);
                    graph.addNode(i,j,value);
                }
                System.out.println();
            }

            graph.constructGraph();
            //System.out.println(graph);
            Solver solver = new Solver(graph);
            solver.solve(order);
//            solver.printSolution();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
//        for(int i=0;i<6;i++) Demo.DataLoader(i,Node.brelaz);
//        System.out.println("only sdf");
        Demo.DataLoader(6,Node.position);
    }
}
