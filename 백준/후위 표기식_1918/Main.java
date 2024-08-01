import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.Stack;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Set<String> OPERATING_SYMBOL_1 = Set.of("+", "-");
    private static final Set<String> OPERATING_SYMBOL_2 = Set.of("/", "*");
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSED_BRACKET = ")";

    public static String solution(String[] equation) {
        Stack<String> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (String component : equation) {
            if (component.equals(OPEN_BRACKET)) {
                stack.push(component);
                continue;
            }
            if (component.equals(CLOSED_BRACKET)) {
                while (!stack.isEmpty() && !stack.peek().equals(OPEN_BRACKET)) {
                    result.append(stack.pop());
                }
                stack.pop();
                continue;
            }
            if (OPERATING_SYMBOL_1.contains(component) || OPERATING_SYMBOL_2.contains(component)) {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(component)) {
                    result.append(stack.pop());
                }
                stack.push(component);
                continue;
            }
            result.append(component);
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    private static int getPriority(String operator) {
        if (OPERATING_SYMBOL_1.contains(operator)) {
            return 1;
        }
        if (OPERATING_SYMBOL_2.contains(operator)) {
            return 2;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {

        String[] equation = br.readLine().split("");

        bw.write(solution(equation));
        bw.close();
        br.close();
    }
}
