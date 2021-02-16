package Assignment2;

import java.util.Stack;

public class StackParser {
    public static Boolean Parsing(String s){
        if(s == null || s.length()%2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '['|| c == '{'|| c== '(' || c == '<'){
                stack.push(c);
            }
            else {
                if (stack.empty()) {
                    return false;
                }
                if ((c == ']' && stack.peek() != '[') || (c == '}' && stack.peek() != '{')) {
                    return false;
                }
                if (c == ')' && stack.peek() != '(') {
                    return false;
                }
                if (c == '>' && stack.peek() != '<') {
                    return false;
                }
                stack.pop();

            }

        }
        return stack.empty();
    }
}
