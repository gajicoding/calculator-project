package level3;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

class MyScanner {
    private final Scanner sc = new Scanner(System.in);

    // 기본 생성자 유지

    Optional<Double> inputDoubleNum() {
        double num;

        try {
            num = sc.nextDouble();
            sc.nextLine(); // 버퍼 비우기

            return Optional.of(num);
        } catch (InputMismatchException e){
            System.out.println("잘못된 입력: " + "실수를 입력해야 한다." + "\n");
            sc.nextLine(); // 버퍼 비우기
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return Optional.empty();
    }

    char inputSymbol() {
        try{
            return sc.nextLine().charAt(0);
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }

        return Const.NOT_SUPPORTED_SYMBOL;
    }

    boolean isExit() {
        try{
            if(sc.nextLine().equals("exit")){
                return true;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return false;
    }

    void close() {
        sc.close();
    }
}
