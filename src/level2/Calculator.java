package level2;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Calculator {
    Queue<Integer> result = new LinkedList<>();

    // 기본 생성자 유지

    public Queue<Integer> getResult() {
        return result;
    }

    public Optional<Integer> calculate(int num1, int num2, char symbol) {
        int res;
        try {
            res = switch (symbol) {
                case '+' -> num1 + num2;
                case '-' -> num1 - num2;
                case '*' -> num1 * num2;
                case '/' -> num1 / num2;
                default -> throw new Exception("사칙연산(+, -, *, /) 기호를 입력해야 한다.");
            };
            this.result.add(res);

            return Optional.of(res);
        } catch(Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return Optional.empty();
    }

    public void popResult() {
        result.poll();
    }
}
