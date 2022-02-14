import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

public class Solution {
    public static int solution(int[] entrances, int[] exits, int[][] path) {
        int[][] transformedGrid = transformGrid(entrances, exits, path);
        int maxFlow = max_flow(transformedGrid);
        return maxFlow;
    }
    private static int[][] transformGrid(int[] entrances, int[] exits, int[][] path)
    {
        int previousLength = path.length;
        int newLength = previousLength + 2;
        int[][] newGrid = new int[newLength][newLength];
        for(int i = 0 ; i < previousLength ;i ++)
            for(int j = 0 ; j < previousLength ; j++)
                newGrid[i+1][j+1] = path[i][j];
        for(int entrance : entrances)
            newGrid[0][entrance+1] = Integer.MAX_VALUE;
        for(int exit : exits)
            newGrid[exit+1][newLength-1] = Integer.MAX_VALUE;
        return newGrid;
    }
    private static int max_flow(int[][] grid)
    {
        int maxFlow = 0;
        List<Integer> path;
        while((path = bfs(grid))!=null)
        {
            int residual_cap = Integer.MAX_VALUE;
            int u = 0;
            for(int v : path)
            {
                residual_cap = Math.min(residual_cap,grid[u][v]);
                u = v;
            }
            maxFlow += residual_cap;
            u = 0;
            for(int v : path)
            {
                grid[u][v] -= residual_cap;
                grid[v][u] += residual_cap;
                u = v;
            }
        }
        return maxFlow;
    }
    private static List<Integer> bfs(int[][] grid)
    {
        int[] parent = new int[grid.length];
        Arrays.fill(parent,-1);
        Queue<Integer>queue = new ArrayDeque<>();
        queue.add(0);
        while(!queue.isEmpty() && parent[parent.length-1]==-1)
        {
            int u = queue.remove();
            for(int i = 0 ; i < parent.length; i++)
            {
                if(grid[u][i]>0 && parent[i]==-1)
                {
                    queue.add(i);
                    parent[i] = u;
                }
            }
        }
        List<Integer>path = new ArrayList<>();
        int u = parent[parent.length-1];
        while(u!=0)
        {
            if(u==-1) return null;
            path.add(u);
            u = parent[u];
        }
        Collections.reverse(path);
        return path;
    }
}