package level3;

import java.util.Arrays;
import java.util.Optional;

import static level3.Operator.findBySymbol;

public class App {
    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner();
        ArithmeticCalculator calculator = new ArithmeticCalculator();

        Number[] nums = new Number[2];
        char symbol;
        Optional<Number> result;

        System.out.println("계산기 프로그램을 시작합니다..");

        while(true) {
            // 두 수 입력 (double 형)
            System.out.print("첫 번째 수를 입력하시오: ");
            nums[0] = myScanner.inputDoubleNum().orElse(null);
            System.out.print("두 번째 수를 입력하시오: ");
            nums[1] = myScanner.inputDoubleNum().orElse(null);
            if(nums[0] == null || nums[1] == null) {
                continue;
            }

            // 연산 기호 입력
            System.out.print("사칙연산(+, -, *, /) 기호를 입력하시오: ");
            symbol = myScanner.inputSymbol();
            if(findBySymbol(symbol) == null) {
                continue;
            }

            // 조건에 따라 int 형으로 변환
            if (isIntegerNums(nums) && symbol != Operator.DIV.getSymbol()) {
                nums = convertIntegerNums(nums);
            }

            // 계산
            result = calculator.calculate(nums[0], nums[1], symbol);
            if(result.isEmpty()){
                continue;
            }

            calculator.addHistory(result.get());
            System.out.println("계산 결과: " + result.get());

            // 반복문 종료
            System.out.print("종료하려면 \"exit\"를 입력하시오 (계속하려면 아무거나 입력): ");
            if(myScanner.isExit()) {
                break;
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------");

        String history = calculator.getHistory().toString();
        System.out.println(history);
        System.out.println("입력된 수보다 큰 기록만 출력합니다.");
        System.out.print("숫자를 입력하시오: ");
        Optional<Double> threshold = myScanner.inputDoubleNum();

        if(threshold.isPresent()) {
            history = calculator.getHistoryGreaterThan(threshold.get()).toString();
        }

        System.out.print("\n최종 기록: " + history + "\n");
        System.out.println("계산기 프로그램이 종료되었습니다.");

        myScanner.close();
    }

    private static boolean isIntegerNums(Number[] nums) {
        return nums[0].doubleValue() % 1 == 0 && nums[1].doubleValue() % 1 == 0;
    }

    private static Number[] convertIntegerNums(Number[] nums) {
        // stream 활용
        return Arrays.stream(nums)
                .map(Number::intValue)
                .toArray(Number[]::new);
    }
}
