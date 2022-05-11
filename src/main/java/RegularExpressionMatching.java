import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RegularExpressionMatching
 * @Description LeetCode 10
 * @Author Yancey
 * @Date 2022/5/11 14:26
 * @Version 1.0
 */
public class RegularExpressionMatching {
    Map<String, Boolean> map = new HashMap<>();
    public boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0);
    }

    private boolean dp(String s, int i, String p, int j) {
        int m = s.length(), n = s.length();
        if(j == n){
            return i == m;
        }
        if(i==m){
            if((n-j)%2 == 1){
                return false;
            }
            for(; j+1<n; j+=2){
                if(p.charAt(j+1) != '*'){
                    return false;
                }
            }
            return true;
        }
        String key = i+","+j;
        if(map.containsKey(key)){
            return map.get(key);
        }
        boolean res = false;
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
            if(j<n-1 && p.charAt(j+1) == '*'){
                res = dp(s, i, p, j+2) || dp(s, i+1, p, j);
            }else{
                res = dp(s, i+1, p, j+1);
            }
        }else{
            if(j<n-1 && p.charAt(j+1) == '*'){
                res = dp(s, i, p, j+2);
            }else{
                return false;
            }
        }
        map.put(key, res);
        return res;
    }
}
