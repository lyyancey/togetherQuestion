import org.junit.Test;

/**
 * @ClassName RegularExpressionMatchingTest
 * @Description TODO
 * @Author Yancey
 * @Date 2022/5/11 15:05
 * @Version 1.0
 */
public class RegularExpressionMatchingTest {
    @Test
    public void testRegularExpressionMatching(){
        String s = "aa";
        String p = "a";
        RegularExpressionMatching regx = new RegularExpressionMatching();
        regx.isMatch(s, p);
    }
}
