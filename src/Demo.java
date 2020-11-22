import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Demo
{
    static String[] dataset = {"alien.txt","d-10-01.txt","d-10-06.txt","d-10-07.txt","d-10-08.txt","d-10-09.txt","d-15-01.txt"};
    static Graph graph;

    public static void DataLoader()
    {
        try {
            File file = new File(dataset[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            str = br.readLine();
            str = str.replace(";","");
            String[] s = str.split("=");
            int dim = Integer.parseInt(s[1]);
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
                    System.out.print( data[j] + " ");

                    graph.addNode(i,j,Integer.parseInt(data[j]));
                }
                System.out.println();
            }

            graph.constructGraph();
            System.out.println(graph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo.DataLoader();
    }
}
