package net.Riddick;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    static Scanner scanner = new Scanner(System.in);
    static int N = 10;

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

    /**
    * 1. Заполнить массив случайными целыми числами. Вывести массив на экран. Переупорядочить в этом массиве
    *  элементы следующим образом: сначала по не убыванию нечетные числа, потом нули, потом прочие числа по
    * не возрастанию. Вывести массив на экран.
     */
    private static void task1() {
        System.out.println("Задача 1:");
        ArrayList<Integer> arr = getArray(N);

        //упорядочивание
        ArrayList<Integer> sub1 = new ArrayList<>();
        ArrayList<Integer> sub2 = new ArrayList<>();
        int l = 0;
        for (int i = 0; i < arr.size(); i++) {
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

    /**
    * 2. Найти в массиве наиболее часто встречающееся число (числа, если таких несколько),
    * вывести на экран исходные данные и результаты.
     */
    private static void task2() {
        System.out.println("Задача 2:");
        ArrayList<Integer> arr = getArray(N);

        //подсчёт вхождений уникальных элементов
        int[][] counter = new int[arr.size()][2];
        int j = 0;
        for (int i = 0; i < arr.size(); i++)
            if (!isUnique(counter, arr.get(i)))
                counter[getIndex(counter, arr.get(i))][1] += 1;
            else {
                counter[j][0] = arr.get(i);
                counter[j++][1] = 1;
            }

        //поиск максимального
        int max = counter[0][1];
        for (int i = 1; i < counter.length; i++)
            if (counter[i][1] > max)
                max = counter[i][1];

        //вывод максимальных на экран
        System.out.print("Самые частые вхождения: ");
        for (int i = 0; i < counter.length; i++) {
            if (counter[i][1] == max) {
                System.out.print(counter[i][0] + " (" + counter[i][1] + " раза)");
                if (i + 1 < counter.length)
                    System.out.print("\t");
            }
        }
    }

    private static void task3() {
    }

    private static void task4() {
    }

    /**
    * Функция заполнения массива случайными элементами
     */
    private static ArrayList<Integer> getArray(int N) {
        //заполнение массива случайными значениями и вывод на экран
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < N; i++)
            arr.add(random.nextInt(10));
        System.out.println("Вход: " + arr);
        return arr;
    }

    /**
     * Функция сортировки числового массива 'array' по возрастанию
     */
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

    /**
     * Функция сортировки числового массива 'array' по убыванию
     */
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

    /**
     * Функция для поиска элемента 'x' в числовом массиве 'arr'
     */
    private static boolean isUnique(int[][] arr, int x) {
        for(int i = 0; i < arr.length; i++)
            if(arr[i][0] == x)
                return false;
        return true;
    }

    /**
    * Функция получения индекса уникального элемента 'x' в массиве 'arr'
     */
    private static int getIndex(int[][] arr, int x) {
        for(int i = 0; i < arr.length; i++){
            if(arr[i][0] == x)
                return i;
        }
        return -1;
    }
}
