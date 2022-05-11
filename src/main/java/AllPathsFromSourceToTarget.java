import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName AllPathsFromSourceToTarget
 * @Description LeetCode 797
 * @Author Yancey
 * @Date 2022/5/10 14:53
 * @Version 1.0
 */
public class AllPathsFromSourceToTarget {
    /**
     * @Author Yancey
     * @Description LeetCode 797
     * @Date 14:55 2022/5/10
     * @Param graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j])
     * @return
     **/
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int i, LinkedList<Integer> path) {
        int n = graph.length;
        path.addLast(i);
        if(i == n-1){
            res.add(new ArrayList<>(path));
        }
        for(int v : graph[i]){
            traverse(graph, v, path);
        }
        path.removeLast();
    }
}
