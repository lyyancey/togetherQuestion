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
    /**
     * @Author Yancey
     * @Description //TODO
     * @Date 7:56 2022/5/16
     * @Param s 模式串
     * @Param p 匹配串
     * @return
     **/
    public boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0);
    }

    private boolean dp(String s, int i, String p, int j) {
        int m = s.length(), n = p.length();
        if(j == n){ // 当匹配到匹配串最后时
            return i == m;// 模式串匹配到最后则恰好匹配，否则两串则不匹配
        }
        if(i==m){// 当模式串走到最后时
            if((n-j)%2 == 1){ //匹配串剩下的长度为奇数时
                return false; // 不可能匹配，返回false
            }
            //为偶数时， 只有 a*b*c*这种格式才能匹配
            for(; j+1<n; j+=2){
                if(p.charAt(j+1) != '*'){
                    return false;
                }
            }
            return true;
        }
        // 检查备忘录
        String key = i+","+j;
        if(map.containsKey(key)){
            return map.get(key);
        }
        boolean res = false;
        boolean b = j < n - 1 && p.charAt(j + 1) == '*';
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){ //如果当前两字符相同，
            if(b){// 且下一字符为通配符， 则
                res = dp(s, i, p, j+2) || //可以匹配0次
                        dp(s, i+1, p, j); // 也可以继续和下一个字符匹配
            }else{// 否则
                res = dp(s, i+1, p, j+1); // 只能继续匹配下一字符
            }
        }else{ // 如果当前两字符不相同
            if(b){ // 且下一字符是通配符
                res = dp(s, i, p, j+2); // 则匹配零次，直接跳过当前字符
            }else{// 否则
                return false; // 两个字符串不匹配，直接返回结果
            }
        }
        map.put(key, res);
        return res;
    }
}
