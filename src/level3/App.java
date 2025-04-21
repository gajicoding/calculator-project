package level3;


import java.util.Arrays;
import java.util.Optional;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner();
        ArithmeticCalculator calculator = new ArithmeticCalculator();

        Optional<Integer> inputOpt;
        Number[] nums;
        char symbol;
        Optional<Number> result;

        System.out.println("계산기 프로그램을 시작합니다..");

        while(true) {
//            System.out.print("정수 계산은 1, 실수 계산은 2를 입력하시오: ");
//            inputOpt = myScanner.inputIntNum();
//            if(inputOpt.isEmpty()) {
//                continue;
//            }
//            if(inputOpt.get() != 1 && inputOpt.get() != 2) {
//                System.out.println("잘못된 입력: " + "1이나 2를 입력해야 한다." + "\n");
//                continue;
//            }
//
//            System.out.printf("두 개의 %s를 입력하시오: ", inputOpt.get() == 1 ? "정수" : "실수");
//            if(inputOpt.get() == 1) {
//                nums = myScanner.inputIntNums();
//            } else {
//                nums = myScanner.inputDoubleNums();
//            }

            System.out.print("두 개의 수를 입력하시오: ");
            nums = myScanner.inputDoubleNums();

            System.out.print("사칙연산(+, -, *, /) 기호를 입력하시오: ");
            symbol = myScanner.inputSymbol();


            if(nums[0].doubleValue() % 1 == 0  && nums[1].doubleValue() % 1 == 0){
                nums = Arrays.stream(nums)
                        .map(Number::intValue)
                        .toArray(Number[]::new);
            }
            if(symbol == Operator.DIV.getSymbol()){
                nums = Arrays.stream(nums)
                        .map(Number::doubleValue)
                        .toArray(Number[]::new);
            }

            result = calculator.calculate(nums[0], nums[1], symbol);
            if(result.isEmpty()){
                continue;
            }
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
            history = calculator.printHistoryGreaterThan(threshold.get()).toString();
        }

        System.out.print("\n최종 기록: " + history + "\n");
        System.out.println("계산기 프로그램이 종료되었습니다.");


        myScanner.close();
    }
}
