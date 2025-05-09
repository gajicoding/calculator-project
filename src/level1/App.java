package level1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num1, num2, result;
        char symbol;

        System.out.println("계산기 프로그램을 시작합니다..");

        while(true) {
            try {
                System.out.print("양의 정수(0포함) 2개를 입력하시오: ");
                num1 = sc.nextInt();
                num2 = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기

                // 양의 정수 조건 체크
                if(num1 < 0 || num2 < 0) {
                    throw new Exception("양의 정수(0포함)을 입력해야 한다.");
                }

                System.out.print("사칙연산(+, -, *, /) 기호를 입력하시오: ");
                symbol = sc.nextLine().charAt(0);

                // /0 체크
                if(num2 == 0 && symbol == '/') {
                    throw new ArithmeticException("0으로 나눌 수 없다.");
                }

                // 계산
                result = switch (symbol) {
                    case '+' -> num1 + num2;
                    case '-' -> num1 - num2;
                    case '*' -> num1 * num2;
                    case '/' -> num1 / num2;
                    default -> throw new Exception("사칙연산(+, -, *, /) 기호를 입력해야 한다.");
                };
                System.out.println("계산 결과: " + result);

                System.out.print("종료하려면 \"exit\"를 입력하시오 (계속하려면 아무거나 입력): ");
                if(sc.nextLine().equals("exit")) break;
                System.out.println();

            } catch(InputMismatchException e) {
                System.out.println("잘못된 입력: " + "양의 정수(0포함)을 입력해야 한다." + "\n");
                sc.nextLine(); // 버퍼 비우기
            } catch (Exception e) {
                System.out.println("잘못된 입력: " + e.getMessage() + "\n");
            }
        }

        System.out.println("계산기 프로그램이 종료되었습니다.");

        sc.close();
    }
}