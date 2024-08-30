class Solution {

    private static final int[][] empty = new int[][] {};

    private int n;
    private int[][] edges;
    private int source;

    private int[] distance;

    private void search() {
        Arrays.fill(distance, -1);
        distance[source] = 0;
        for (var i = 0; i < n; i++) {
            var stop = true;
            for (var edge : edges) {
                var a = edge[0];
                var b = edge[1];
                var w = edge[2];
                if (distance[a] != -1) {
                    var newDistanceB = (long) distance[a] + w;
                    if (distance[b] == -1 || newDistanceB < distance[b]) {
                        distance[b] = (int) newDistanceB;
                        stop = false;
                    }
                }
                if (distance[b] != -1) {
                    var newDistanceA = (long) distance[b] + w;
                    if (distance[a] == -1 || newDistanceA < distance[a]) {
                        distance[a] = (int) newDistanceA;
                        stop = false;
                    }
                }
            }
            if (stop) {
                break;
            }
        }
    }

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        this.n = n;
        this.edges = edges;
        this.source = source;
        distance = new int[n];
        var e = edges.length;
        var modifiable = new boolean[e];
        for (var i = 0; i < e; i++) {
            if (edges[i][2] == -1) {
                modifiable[i] = true;
                edges[i][2] = target;
            }
        }
        search();
        // System.out.println(Arrays.toString(distance));
        if (distance[destination] < target) {
            return empty;
        }
        if (distance[destination] == target) {
            return edges;
        }
        for (var i = 0; i < e; i++) {
            if (!modifiable[i]) {
                continue;
            }
            edges[i][2] = 1;
            search();
            if (distance[destination] <= target) {
                edges[i][2] = target - distance[destination] + 1;
                return edges;
            }
        }
        return empty;
    }
}