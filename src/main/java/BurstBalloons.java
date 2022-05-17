/**
 * @ClassName BurstBalloons
 * @Description LeetCode 312
 * @Author Yancey
 * @Date 2022/5/17 8:06
 * @Version 1.0
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n+2]; // 新建数组
        newNums[0] = newNums[n+1] = 1; //在数组两端添加虚拟的气球，数值为1
        for(int i=0; i<n; i++){ // 循环复制数组
            newNums[i+1] = nums[i];
        }
        // base case dp[i][j] 表示扎破(i, j)之间的气球能获取的最大分数是多少
        int[][] dp = new int[n+2][n+2];
        for(int i=n; i>=0; i--){ // 由下向上遍历
            for(int j=i+1; j<n+2; j++){ // 由左向右遍历
                for(int k=i+1; k<j; k++){// 假设k为(i, j)之间最后扎破的气球
                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i][k]+dp[k][j]+newNums[i]*newNums[k]*newNums[j]
                    );
                }
            }
        }
        return dp[0][n+1];
    }
}
