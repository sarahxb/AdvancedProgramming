//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        if ( args.length <2 ) {
            System.out.println( "Error: Wrong number of arguments!" );
        }
        int n,k;

        n= Integer.parseInt(args[0]);
        k= Integer.parseInt(args[1]);

        if ( n < k ) {
            System.out.println( "Error: Wrong arguments! n < k" );
        }

        //System.out.println("\u25A0 \u25A1 ⬛ ⬜ █ ░ ■ □");
        long t1=System.currentTimeMillis()/1000L;

        Graph graph = new Graph(n, k);

        graph.generateGraph();
        System.out.println(graph);
        //graph.MaxDegree();
        //graph.MinDegree();
        graph.testClique();
       if( graph.hasStableSetOfSizeK())
           System.out.println("The graph has a stable set of size k" );
       else
           System.out.println("The graph doesn't a stable set of size k" );
     /*int m= graph.NumberEdges();
     System.out.println("Number of edges (m):  " + m);
       graph.MaxDegree();
       graph.MinDegree();

       int sum= graph.DegreesSum();
       System.out.println("Number of degrees sum: " + sum);
       if(sum == 2*m)
           System.out.println( "The sum of all degrees is 2*m" );



*/
        long t2=System.currentTimeMillis()/1000L;
        System.out.println("Time taken: " + (t2-t1));
        }

    }

    class Graph {
        private int n, k;
        private int[][] adjacencyMatrix;
        private Random rand = new Random();

        public Graph(int n, int k) {
            this.n = n;
            this.k = k;
            adjacencyMatrix = new int[n][n];
        }

        private List<Integer> getRandomNodes() {
            List<Integer> nodes = new ArrayList<>();
            while (nodes.size() < k) {
                int candidate = rand.nextInt(n);

                if (!nodes.contains(candidate)) {
                    nodes.add(candidate);
                }
            }
            return nodes;
        }

        private void ensureCliqueAndSubset() {
            List<Integer> Cnodes = getRandomNodes();
            List<Integer> Snodes = new ArrayList<>();

            //daca vreau un nod  comun intre clica si stable set
            //int commonNode = Cnodes.get(rand.nextInt(k));
            //Snodes.add(commonNode);


            while (Snodes.size() < k) {
                int i = rand.nextInt(n);
                if (!Cnodes.contains(i) && !Snodes.contains(i)) {
                    Snodes.add(i);

                }
            }

            for (int i = 0; i < Cnodes.size() - 1; i++)
                for (int j = i + 1; j < Cnodes.size(); j++) {
                    adjacencyMatrix[Cnodes.get(i)][Cnodes.get(j)] = 1;
                    adjacencyMatrix[Cnodes.get(j)][Cnodes.get(i)] = 1;
                    adjacencyMatrix[Snodes.get(i)][Snodes.get(j)] = 0;
                    adjacencyMatrix[Snodes.get(j)][Snodes.get(i)] = 0;
                }
        }

        private void fillRandom() {
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    if (adjacencyMatrix[i][j] == 0 && adjacencyMatrix[j][i] == 0) {
                        adjacencyMatrix[i][j] = rand.nextBoolean() ? 1 : 0;
                        adjacencyMatrix[j][i] = adjacencyMatrix[i][j];
                    }
        }

        public void generateGraph() {
            ensureCliqueAndSubset();
            fillRandom();
        }


        public String toString() {
            StringBuilder sb = new StringBuilder();
            System.out.println("Adjacency Matrix:\n");

            System.out.print(" ");
            System.out.print(" ");
            for (int i = 0; i < n; i++) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();

            for (int i = 0; i < n; i++) {
                System.out.print(i);
                System.out.print(" ");
                for (int j = 0; j < n; j++) {
                    System.out.print(adjacencyMatrix[i][j] == 1 ? "░" : "\u25A0");
                    System.out.print(" ");

                }
                System.out.println();
            }
            return sb.toString();
        }


        public int NumberEdges() {
            int cnt = 0;
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    if (adjacencyMatrix[j][k] == 1)
                        cnt++;
            cnt /= 2;
            return cnt;
        }

        public void MaxDegree() {
            int max = 0;
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < n; k++)
                    if (adjacencyMatrix[j][k] == 1)
                        cnt++;
                if (cnt > max)
                    max = cnt;
            }
            System.out.println('\u0394' + "(G) = " + max);
        }

        public void MinDegree() {
            int min = n - 1;
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < n; k++)
                    if (adjacencyMatrix[j][k] == 1)
                        cnt++;
                if (cnt < min)
                    min = cnt;
            }
            System.out.println("\u03B4" + "(G) = " + min);
        }

        public int DegreesSum() {
            int sum = 0;
            for (int j = 0; j < n; j++) {

                for (int k = 0; k < n; k++)
                    if (adjacencyMatrix[j][k] == 1)
                        sum++;
            }

            return sum;
        }

        public void generateRandomGraph() {
            Random random = new Random();
            int edgesAdded = 0;

            int maxEdges = n * (n - 1) / 2;
            int m = random.nextInt(maxEdges);

            while (edgesAdded < m) {
                int node1 = random.nextInt(n);
                int node2 = random.nextInt(n);

                if (node1 != node2 && adjacencyMatrix[node1][node2] == 0) {
                    adjacencyMatrix[node1][node2] = 1;
                    adjacencyMatrix[node2][node1] = 1;
                    edgesAdded++;
                }
            }
        }

        public void printMatrix() {

            System.out.println("Adjacency Matrix:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }

        }

        private List<Integer> generateCliqueSubset() {
            List<Integer> clique = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            if (findCliqueRecursive(0, currentPath, clique, 0)) {
                return clique;
            }
            return new ArrayList<>();
        }

        private boolean findCliqueRecursive(int start, List<Integer> currentPath, List<Integer> result, int currentSize) {

            if (currentSize == k) {
                result.addAll(currentPath);
                return true;
            }


            for (int i = start; i < n; i++) {
                boolean canBeAdded = true;


                for (int node : currentPath) {
                    if (adjacencyMatrix[i][node] == 0) {
                        canBeAdded = false;
                        break;
                    }
                }


                if (canBeAdded) {
                    currentPath.add(i);
                    if (findCliqueRecursive(i + 1, currentPath, result, currentSize + 1)) return true;

                    currentPath.remove(currentPath.size() - 1);
                }
            }


            return false;
        }


        public boolean verifyClique(List<Integer> subset) {
            for (int i = 0; i < subset.size(); i++)
                for (int j = i + 1; j < subset.size(); j++) {
                    int u = subset.get(i);
                    int v = subset.get(j);

                    if (adjacencyMatrix[u][v] == 0)
                        return false;

                }

                return true;
        }

        public void testClique() {
            List<Integer> subset = generateCliqueSubset();

            if (!subset.isEmpty() && verifyClique(subset)) {
                System.out.println("Graful contine o clica de " + k + " noduri");
            } else {
                System.out.println("Graful nu contine o clica de " + k + " noduri");
            }
        }

        private int[][] generateComplementaryGraph() {
            int[][] complementary = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        complementary[i][j] = (adjacencyMatrix[i][j] == 0) ? 1 : 0;
                    } else {
                        complementary[i][j] = 0;
                    }
                }
            }
            return complementary;
        }

        public boolean hasStableSetOfSizeK() {

            int[][] complementaryMatrix = generateComplementaryGraph();
            int[][] originalMatrix = adjacencyMatrix;
            adjacencyMatrix = complementaryMatrix;
            List<Integer> clique = generateCliqueSubset();

            adjacencyMatrix = originalMatrix;
            
            return !clique.isEmpty();
        }

    }


