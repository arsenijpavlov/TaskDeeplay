package net.Riddick;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Выбор задачи (1-4): ");
        switch (scanner.nextInt()) {
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
        int N = scanner.nextInt();

        //заполнение массива случайными значениями и вывод на экран
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < N; i++)
            arr.add(random.nextInt(10));
        System.out.println("Вход: " + arr);

        //упорядочивание
        ArrayList<Integer> sub1 = new ArrayList<>();
        ArrayList<Integer> sub2 = new ArrayList<>();
        int l = 0;
        for (int i = 0; i < N; i++) {
            if (arr.get(i) == 0)
                l++;
            else if ((arr.get(i) & 1) == 1)
                sub1.add(arr.get(i));
            else
                sub2.add(arr.get(i));
        }
        //сортировка
        sortABC(sub1);
        sortCBA(sub2);

        //заполнение исходного
        int i = 0;
        int f = l;
        for (; i < sub1.size(); i++)
            arr.set(i, sub1.get(i));
        for (; l > 0; l--, i++)
            arr.set(i, 0);
        for (; i < arr.size(); i++)
            arr.set(i, sub2.get(i - f - sub1.size()));

        System.out.println("Выход: " + arr);
    }

    private static void task2() {
    }

    private static void task3() {
    }

    private static void task4() {
    }

    private static void sortABC(ArrayList<Integer> array) {
        //System.out.println("Before sortABC: " + array);
        int i = 0, j = 1;
        while(i < array.size() - 1) {
            if (array.get(i) > array.get(i + 1)) {
                array.set(i, array.get(i) + array.get(i + 1));
                array.set(i + 1, array.get(i) - array.get(i + 1));
                array.set(i, array.get(i) - array.get(i + 1));
                i--;
                if (i == -1)
                    i = j++;
            } else
                i = j++;
        }
        //System.out.println("Post sortABC: " + array);
    }

    private static void sortCBA(ArrayList<Integer> array) {
        //System.out.println("Before sortCBA: " + array);
        int i = 0, j = 1;
        while(i < array.size() - 1) {
            if (array.get(i) < array.get(i + 1)) {
                array.set(i, array.get(i) + array.get(i + 1));
                array.set(i + 1, array.get(i) - array.get(i + 1));
                array.set(i, array.get(i) - array.get(i + 1));
                i--;
                if (i == -1)
                    i = j++;
            } else
                i = j++;
        }
        //System.out.println("Post sortCBA: " + array);
    }
}
