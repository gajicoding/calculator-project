package level3;

public enum Operator {
    ADD('+', (a, b) -> {
        if(a instanceof Integer) {
            return (Integer) a + (Integer) b;
        } else if(a instanceof Double) {
            return (Double) a + (Double) b;
        }
        throw new IllegalArgumentException("지원하지 않는 타입");
    }),
    SUB('-', (a, b) -> {
        if(a instanceof Integer) {
            return (Integer) a - (Integer) b;
        } else if(a instanceof Double) {
            return (Double) a - (Double) b;
        }
        throw new IllegalArgumentException("지원하지 않는 타입");
    }),
    MUL('*', (a, b) -> {
        if(a instanceof Integer) {
            return (Integer) a * (Integer) b;
        } else if(a instanceof Double) {
            return (Double) a * (Double) b;
        }
        throw new IllegalArgumentException("지원하지 않는 타입");
    }),
    DIV('/', (a, b) -> {
        if(a instanceof Integer) {
            if((Integer) b == 0) {
                throw new ArithmeticException("0으로 나눌 수 없다.");
            }
            return (Integer) a / (Integer) b;
        } else if(a instanceof Double) {
            if((Double) b == 0) {
                throw new ArithmeticException("0으로 나눌 수 없다.");
            }
            return (Double) a / (Double) b;
        }
        throw new IllegalArgumentException("지원하지 않는 타입");
    });

    // 속성
    private final char symbol;
    private final Operate<?> op;

    // 함수형 인터페이스
    @FunctionalInterface
    public interface Operate<T> {
        T apply(T a, T b) throws Exception;
    }

    // 생성자
    Operator(char symbol, Operate<?> op){
        this.symbol = symbol;
        this.op = op;
    }

    public char getSymbol() {
        return symbol;
    }

    public <T> Operate<T> getOp() {
        return (Operate<T>) op;
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
