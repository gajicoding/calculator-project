package level3;

enum Operator {
    ADD('+', (a, b) -> a.doubleValue() + b.doubleValue()),
    SUB('-', (a, b) -> a.doubleValue() - b.doubleValue()),
    MUL('*', (a, b) -> a.doubleValue() * b.doubleValue()),
    DIV('/', (a, b) -> {
        if(b.intValue() == 0) {
            throw new ArithmeticException("0으로 나눌 수 없다.");
        }
        return a.doubleValue() / b.doubleValue();
    });

    // 속성
    private final char symbol;
    private final Operate<Number> op;

    // 함수형 인터페이스
    @FunctionalInterface
    public interface Operate<T extends Number> {
        T apply(T a, T b) throws Exception;
    }

    // 생성자
    Operator(char symbol, Operate<Number> op){
        this.symbol = symbol;
        this.op = op;
    }

    public char getSymbol() {
        return symbol;
    }

    public <T extends Number> T invoke(T a, T b) throws Exception {
        Number result;

        if(a instanceof Integer){
            result = op.apply(a, b).intValue();
        } else if(a instanceof Double){
            result = op.apply(a, b).doubleValue();
        } else {
            throw new IllegalArgumentException("정수나 실수 타입이 아닙니다.");
        }

        @SuppressWarnings("unchecked")
        T castedResult = (T) result;

        return castedResult;
    }

    public static Operator findBySymbol(char symbol) {
        for (Operator op: values()) {
            if (op.symbol == symbol) {
                return op;
            }
        }
        return null;
    }
}
