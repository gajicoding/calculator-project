package level2;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class MyScanner {
    Scanner sc = new Scanner(System.in);

    public Optional<int[]> inputNums() {
        int[] nums = new int[2];

        try {
            nums[0] = sc.nextInt();
            nums[1] = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            // 양의 정수 조건 체크
            if(nums[0] < 0 || nums[1] < 0) {
                throw new Exception("양의 정수(0포함)을 입력해야 한다.");
            }

            return Optional.of(nums);

        } catch (InputMismatchException e){
            System.out.println("잘못된 입력: " + "양의 정수(0포함)을 입력해야 한다." + "\n");
            sc.nextLine(); // 버퍼 비우기
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
            return Optional.empty();
        }
    }

    public char inputSymbol() {
        try{
            return sc.nextLine().charAt(0);
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return '\0';
    }

    public Boolean inputExit() {
        try{
            if(sc.nextLine().equals("exit")){
                return true;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력: " + e.getMessage() + "\n");
        }
        return false;
    }

    public Boolean inputY() {
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
