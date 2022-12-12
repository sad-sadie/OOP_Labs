package com.example.myapplication;

import java.util.List;

public class Graph<T> {
    private boolean[][] matrix;
    private List<T> data;

    public Graph(List<T> data) {
        this.data = data;
        this.matrix = new boolean[data.size()][data.size()];
    }

    public void addEdge(T v1, T v2) {
        int i = data.indexOf(v1);
        int j = data.indexOf(v2);
        this.matrix[i][j] = true;
        this.matrix[j][i] = true;
    }

    public void removeEdge(T v1, T v2) {
        int i = data.indexOf(v1);
        int j = data.indexOf(v2);
        this.matrix[i][j] = false;
        this.matrix[j][i] = false;
    }

    public boolean DFS(T startV, T wantedV) {
        boolean[] visited = new boolean[this.matrix.length];
        int start = data.indexOf(startV);
        int wanted = data.indexOf(wantedV);
        return DFSutil(start, wanted, visited);
    }

    private boolean DFSutil(int current, int wanted, boolean[] visited) {
        visited[current] = true;

        boolean result = false;
        for (int i = 0; i < this.matrix.length; ++i)
            if (this.matrix[current][i] && !visited[i]) {
                if (i == wanted)
                    return true;
                result = DFSutil(i, wanted, visited);
                if (result)
                    return result;
            }

        return false;
    }
}

