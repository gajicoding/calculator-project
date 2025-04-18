package level2;

import java.util.Optional;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner();
        Calculator calculator = new Calculator();

        int[] nums;
        char symbol;
        Optional<Integer> res;
        Queue<Integer> result;

        System.out.println("계산기 프로그램을 시작합니다..");

        while(true) {
            System.out.print("양의 정수(0포함) 2개를 입력하시오: ");
            nums = myScanner.inputNums().orElse(null);
            if(nums == null) {
                continue;
            }

            System.out.print("사칙연산(+, -, *, /) 기호를 입력하시오: ");
            symbol = myScanner.inputSymbol();
            if(symbol == '\0'){
                continue;
            }

            // /0 체크
            if(nums[1] == 0 && symbol == '/') {
                System.out.println("잘못된 입력: " + "0으로 나눌 수 없다." + "\n");
                continue;
            }

            res = calculator.calculate(nums[0], nums[1], symbol);
            if(res.isEmpty()){
                continue;
            }
            System.out.println("계산 결과: " + res.get());

            System.out.print("종료하려면 \"exit\"를 입력하시오 (계속하려면 아무거나 입력): ");
            if(myScanner.inputExit()){
                break;
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------");

        // 결과 관리 (삭제)
        while(true) {
            result = calculator.getResult();

            if(result.isEmpty()) {
                System.out.print("남은 기록이 없습니다.");
                break;
            }

            System.out.print("저장된 결과 기록 확인: " + calculator.getResult() + "\n");

            System.out.print("최근 기록을 삭제하겠습니까? (y, n): ");
            if(myScanner.inputY()){
                calculator.popResult();
            }

            System.out.print("종료하려면 \"exit\"를 입력하시오 (계속하려면 아무거나 입력): ");
            if(myScanner.inputExit()){
                break;
            }
            System.out.println();
        }


        // 마무리
        result = calculator.getResult();
        if(!result.isEmpty()) {
            System.out.print("\n최종 기록: " + result + "\n");
        }

        System.out.println("계산기 프로그램이 종료되었습니다.");

        myScanner.close();
    }
}