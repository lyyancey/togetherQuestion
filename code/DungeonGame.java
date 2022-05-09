import java.util.Arrays;

/**
 * @ClassName DungeonGame
 * @Description Leet Code 174
 * @Author Yancey
 * @Date 2022/5/9 13:28
 * @Version 1.0
 */
public class DungeonGame {
    /* 备忘录数组, 用来消除重叠子问题*/
    int[][] memo;
    /**
     * @Author Yancey
     * @Description LeetCode 174
     * @Date 13:36 2022/5/9
     * @Param dungeon
     * @return int
     **/
    public int calculateMinimumHP(int[][] dungeon){
        int m = dungeon.length;
        int n = dungeon[0].length;
        memo = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(memo[i], -1);
        }
        return dp(dungeon, 0, 0, m, n);
    }
    private int dp(int[][] grid, int i, int j, int m, int n){
        // base case
        if(i == m-1 && j == n-1){
            return grid[i][j] >= 0 ? 1 : -grid[i][j]+1;
        }
        //边界处理
        if(i == m || j == n){
            return Integer.MAX_VALUE;
        }
        // 备忘录，消除重叠子问题
        if(memo[i][j] != -1){
            return memo[i][j];
        }
        //向下向右， 取最小值
        int res = Math.min(
                // 向下移动
                dp(grid, i+1, j, m, n),
                //向右移动
                dp(grid, i, j+1, m, n)
        )-grid[i][j];
        // 血量最低为 1，不能为负数
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }
    /**
     * @Author Yancey
     * @Description LeetCode 174 尝试遍历解法
     * @Date 14:12 2022/5/9
     * @Param null 1
     * @return
     **/
    public int calculateMinimumHP_(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // 先求出右下角的值
        dp[m-1][n-1] = Math.max(0, -grid[m-1][n-1]);
        // 设置最后一列的值
        for(int i=m-2; i>=0; i--){
            dp[i][n-1] = Math.max(0, dp[i+1][n-1]-grid[i][n-1]);
        }
        // 计算最后一列的值
        for(int i=n-2; i>=0; i--){
            dp[m-1][i] = Math.max(0, dp[m-1][i+1]-grid[m-1][i]);
        }
        // 倒着循环递推每一行的值
        for(int i=m-2; i>=0; i--){
            for(int j=n-2; j>=0; j--){
                int res = Math.min(dp[i+1][j], dp[i][j+1])-grid[i][j];
                dp[i][j] = Math.max(0, res);
            }
        }
        // 这里为了保证落地不死，所以要加一
        return dp[0][0]+1;
    }
}
