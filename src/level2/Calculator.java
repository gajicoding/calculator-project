package level2;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Calculator {
    Queue<Integer> history = new LinkedList<>();

    // 기본 생성자 유지

    public Queue<Integer> getHistory() {
        return history;
    }

    public Optional<Integer> calculate(int[] nums, char symbol) {
        int result;

        try {
            // /0 체크
            if(nums[1] == 0 && symbol == '/') {
                throw new ArithmeticException("0으로 나눌 수 없다.");
            }

            result = switch (symbol) {
                case '+' -> nums[0] + nums[1];
                case '-' -> nums[0] - nums[1];
                case '*' -> nums[0] * nums[1];
                case '/' -> nums[0] / nums[1];
                default -> throw new Exception("사칙연산(+, -, *, /) 기호를 입력해야 한다.");
            };

            this.history.add(result);

            return Optional.of(result);

        } catch(Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return Optional.empty();
    }

    public void removeOldestHistory() {
        history.poll();
    }
}
