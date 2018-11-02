package Stack;

import java.util.Stack;

/**
 * Created by Jline on 2018/11/2.
 */
public class LeetCode_20 {
    public boolean isValid( String s ) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{')
                stack.push(s.charAt(i));
            else {
                if (stack.isEmpty())
                    return false;
                char topChar = stack.pop();
                if (c == ')' && topChar != '(')
                    return false;
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main( String[] args ) {
        System.out.println(new LeetCode_20().isValid("([])"));
        System.out.println(new LeetCode_20().isValid("([})"));
    }
}
