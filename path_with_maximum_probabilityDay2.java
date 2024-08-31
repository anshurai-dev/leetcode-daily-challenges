

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Create an adjacency list where each node has a list of its neighbors and the corresponding probabilities
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];
            graph.get(u).add(new Pair(v, prob));
            graph.get(v).add(new Pair(u, prob));
        }

        // Probability to reach each node, initialized to 0
        double[] maxProb = new double[n];
        maxProb[start] = 1.0; // Start with a probability of 1 at the start node

        // BFS queue to explore the graph level by level
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            // Explore each neighbor of the current node
            for (Pair neighbor : graph.get(node)) {
                int nextNode = neighbor.node;
                double edgeProb = neighbor.probability;

                // Calculate the new probability to reach the neighbor
                double newProb = maxProb[node] * edgeProb;
                // If this new probability is greater than the previously recorded probability, update it
                if (newProb > maxProb[nextNode]) {
                    maxProb[nextNode] = newProb;
                    // Add this neighbor to the queue to further explore its neighbors
                    queue.add(nextNode);
                }
            }
        }

        // Return the probability to reach the end node, or 0 if not reachable
        return maxProb[end];
    }

    // Helper class to store the neighbor node and the probability of reaching it
    class Pair {
        int node;
        double probability;

        Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }
}