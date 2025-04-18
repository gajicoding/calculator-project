package level2;

import java.util.Optional;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner();
        Calculator calculator = new Calculator();

        int[] nums;
        char symbol;
        Optional<Integer> result;
        Queue<Integer> history;

        System.out.println("계산기 프로그램을 시작합니다..");

        // 계산기 반복하기
        while(true) {
            System.out.print("양의 정수(0포함) 2개를 입력하시오: ");
            nums = myScanner.inputNums();
            if(nums == null) {
                continue;
            }

            System.out.print("사칙연산(+, -, *, /) 기호를 입력하시오: ");
            symbol = myScanner.inputSymbol();
            if(symbol == Const.NOT_SUPPORTED_SYMBOL){
                continue;
            }

            result = calculator.calculate(nums, symbol);
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

        // 기록 관리 (삭제)
        while(true) {
            history = calculator.getHistory();

            if(history.isEmpty()) {
                break;
            }

            System.out.print("저장된 결과 기록 확인: " + calculator.getHistory() + "\n");

            System.out.print("가장 오래된 기록을 삭제하겠습니까? (y): ");
            if(myScanner.askRemoveHistory()){
                calculator.removeOldestHistory();
            }

            System.out.print("종료하려면 \"exit\"를 입력하시오 (계속하려면 아무거나 입력): ");

            if(myScanner.isExit()){
                break;
            }
            System.out.println();
        }


        // 마무리
        history = calculator.getHistory();
        System.out.print("\n최종 기록: " + history + "\n");
        System.out.println("계산기 프로그램이 종료되었습니다.");

        myScanner.close();
    }
}
