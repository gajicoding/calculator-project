package level3;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

class ArithmeticCalculator {
    private final Queue<Number> history = new LinkedList<>();

    // 기본 생성자 유지

    public Queue<Number> getHistory() {
        return history;
    }

    // 계산
    public <T extends Number> Optional<T> calculate(T num1, T num2, char symbol) {
        try {
            Operator op = Operator.findBySymbol(symbol);

            if(op == null) {
                throw new NullPointerException("사칙연산(+, -, *, /) 기호를 입력해야 한다.");
            }

            T result = op.invoke(num1, num2);

            return Optional.ofNullable(result);

        } catch(Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return Optional.empty();
    }

    public void addHistory(Number num){
        history.add(num);
    }

    public Queue<Number> getHistoryGreaterThan(Double num){
        // stream 활용
        return history.stream()
                .filter(h -> h.doubleValue() > num)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
