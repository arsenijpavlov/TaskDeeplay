package net.Riddick;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Выбор задачи (1-4): ");
        switch (scanner.nextInt()){
            case 1:
                task1();
                break;
            case 2:
                task2();
                break;
            case 3:
                task3();
                break;
            case 4:
                task4();
                break;
            default:
                System.out.println("Решения задачи с таким номером нет.");
        }
    }

    private static void task1() {
        System.out.print("Количество элементов в массиве: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = scanner.nextInt();

        //заполнение массива случайными значениями и вывод на экран
        int[] arr = new int[N];
        Random random = new Random();
        for(int i : arr) {
            arr[i] = random.nextInt() % 100 - 50;
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //упорядочивание
        //нечётные
        List<Integer> sub1 = null;
        for(int i:arr){
            if((arr[i]&1)==1)
                sub1.add(arr[i]);
            //сортировка
            sort(sub1, true);
        }
        //нули
        for(int i:arr){

        }
        //чётные
        for(int i:arr){

        }

    }

    private static void task2() {
    }

    private static void task3() {
    }

    private static void task4() {
    }

    private static void sort(List<Integer> sub, boolean b) {
        int pl = 0, pr = sub.size();
        int x = sub.get(sub.size()/2);

    }
}
