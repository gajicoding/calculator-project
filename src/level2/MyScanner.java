package level2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyScanner {
    Scanner sc = new Scanner(System.in);

    // 기본 생성자 유지

    public int[] inputNums() {
        int[] nums = new int[2];

        try {
            nums[0] = sc.nextInt();
            nums[1] = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            // 양의 정수 조건 체크
            if(nums[0] < 0 || nums[1] < 0) {
                throw new Exception("양의 정수(0포함)을 입력해야 한다.");
            }

            return nums;

        } catch (InputMismatchException e){
            System.out.println("잘못된 입력: " + "양의 정수(0포함)을 입력해야 한다." + "\n");
            sc.nextLine(); // 버퍼 비우기
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return new int[0];
    }

    public char inputSymbol() {
        try{
            return sc.nextLine().charAt(0);
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }

        return Const.NOT_SUPPORTED_SYMBOL;
    }

    public boolean isExit() {
        try{
            if(sc.nextLine().equals("exit")){
                return true;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return false;
    }

    public boolean askRemoveHistory() {
        try{
            if(sc.nextLine().equalsIgnoreCase("y")){
                return true;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return false;
    }

    public void close() {
        sc.close();
    }
}
