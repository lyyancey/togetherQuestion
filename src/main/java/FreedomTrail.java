import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FreedomTrail
 * @Description LeetCode 514
 * @Author Yancey
 * @Date 2022/5/9 15:16
 * @Version 1.0
 */
public class FreedomTrail {
    /**
     * 备忘录，消除重叠子问题
     **/
    int[][] memo;
    /**
     * 记录字符串环中，每个字符和其索引的位置
     * 由于字符串可能不止一个，所以每个字符串对应的是一个列表
     **/
    Map<Character, List<Integer>> map = new HashMap<>();
    public int findRotateSteps(String ring, String key){
        int m = ring.length();
        int n = key.length();
        // 备忘录初始化为 0
        memo = new int[m][n];
        for(int i=0; i<m; i++){
            Character c = ring.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, new ArrayList());
            }
            map.get(c).add(i);
        }
        return dp(ring, 0, key, 0);
    }
    private int dp(String ring, int i, String key, int j){
        // base case
        if(j == key.length()){
            return 0;
        }
        // 如果计算过, 直接返回结果
        if(memo[i][j] != 0) {
            return memo[i][j];
        }
        // 圆环字符串的长度
        int n = ring.length();
        // 记录每次匹配的结果，  要取最小值，所以初始化最大值
        int res = Integer.MAX_VALUE;
        // 循环计算拨到每个ring中与key[j]相同字符所需要的操作步数
        for(int k : map.get(key.charAt(j))){
            // 将ring[k] 拨到十二点钟方向所需要的操作步数
            int delta = Math.abs(k-i);
            // 逆时针拨与顺时针拨之中取最小值
            delta = Math.min(delta, n-delta);
            //计算key中剩下的字符串所需要的最小操作步数
            int subProblem = dp(ring, k, key, j+1);
            // 取ring中所有相同字符的操作步数的最小值
            res = Math.min(res, delta+subProblem+1);
        }
        // 记录并返回
        memo[i][j] = res;
        return res;

    }
}
