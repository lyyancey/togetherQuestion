import java.util.*;

/**
 * @ClassName CheapestFlightsWithinKStops
 * @Description LeetCode 787
 * @Author Yancey
 * @Date 2022/5/10 8:34
 * @Version 1.0
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args){
        CheapestFlightsWithinKStops chep = new CheapestFlightsWithinKStops();
        int[][] flight = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int res = chep.findCheapestPrice(4, flight, 0, 3,1);
        System.out.println(res);
    }
    private int src; // 记录起点位置，倒着遍历
    private Map<Integer, List<int[]>> map = new HashMap<>();  //记录每个与中点相连的所有起点以及票的价格 <to, [[from0, price0], [from1, price1], ...]>
    private int[][] memo; // 备忘录
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        k++; // 将允许的k站转换为边的条数
        this.src = src;
        memo = new int[n][k+1];
        for(int[] row : memo){
            Arrays.fill(row, -2);
        }
        for(int[] flight : flights){
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            map.putIfAbsent(to, new ArrayList<>());
            map.get(to).add(new int[]{from, price});
        }
        return dfs(dst, k);
    }
    private int dfs(int dst, int k){
        if(dst == src){
            return 0;
        }
        if(k==0){
            return -1;
        }
        if(memo[dst][k] != -2){
            return memo[dst][k];
        }
        int res = Integer.MAX_VALUE;
        if(map.containsKey(dst)){
            for(int[] flight : map.get(dst)){
                int from = flight[0];
                int price = flight[1];
                int subPrice = dfs(from, k-1);
                if(subPrice != -1){
                    res = Math.min(res, subPrice+price);
                }
            }
        }
        memo[dst][k] = res == Integer.MAX_VALUE? -1 : res;
        return memo[dst][k];
    }
}
