import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SuperEggDrop
 * @Description Leet Code Problem 887
 * @Author Yancey
 * @Date 2022/5/16 9:09
 * @Version 1.0
 */
public class SuperEggDrop {
    Map<String, Integer> map;
    public int superEggDrop(int k, int n) {
        map = new HashMap<>();
        return binarySearchDP(k, n);
    }
    // 非二分搜索
    private int dp(int k, int n){
        // base case
        if(k==1) return n;
        if(n==0) return 0;
        String key = k+","+n;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int res = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            res = Math.min(res, Math.max(dp(k-1, i-1), dp(k, n-i))+1);
        }
        map.put(key, res);
        return res;
    }
    private int binarySearchDP(int k, int n){
        if(k==1) return n;
        if(n==0) return 0;
        String key = k+","+n;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int res = Integer.MAX_VALUE;
        int lo=1, hi=n;
        while (lo<=hi){
            int mid = (lo+hi)/2;
            int broken = binarySearchDP(k-1, mid -1);
            int notBroken = binarySearchDP(k, n-mid);
            if(broken > notBroken){
                hi = mid-1;
                res = Math.min(res, broken+1);
            }else {
                lo = mid+1;
                res = Math.min(res, notBroken+1);
            }
        }
        map.put(key, res);
        return res;
    }
}
