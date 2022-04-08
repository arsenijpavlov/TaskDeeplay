package net.Riddick;

import java.util.*;

public class main {
    static Scanner scanner = new Scanner(System.in);
    static int N = 10;
    static int MAX = 10;

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
        ArrayList<Integer> arr = getArray(N,0, MAX, "Вход: ");

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
        ArrayList<Integer> arr = getArray(N,0, MAX, "Вход: ");

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

    /**
     * 3.1 Играют 2 игрока. Каждый из них перед игрой тайно от другого игрока выбирает по одной последовательности
     * из 3 чисел от 1 до 6 (числа могут совпадать). Далее они по очереди бросают кубик определенное число раз
     * (100, 1000, ...). Каждый из игроков получает в игре столько очков, сколько раз выпала последовательность из
     * тех чисел, которые он выбрал. При этом последовательности чисел одного игрока (которые приносят ему очки)
     * не должны пересекаться, а последовательности чисел разных игроков могут пересекаться.
     *
     * 3.2 Необходимо для двух фиксированных наборов 3 чисел для каждого из игроков и фиксированного числа бросков
     * кубика вычислить для обоих игроков вероятность набора игроком большего, чем у соперника, числа очков, а
     * также вероятность ничьей. Достаточно приближенного вычисления,
     * Допустимо применить метод Монте-Карло.
     */
    private static void task3() {
        ArrayList<Integer> player1 = getArray(3, 1, 6, "Игрок 1: ");
        ArrayList<Integer> player2 = getArray(3, 1, 6, "Игрок 2: ");

        ArrayList<Integer> sim1 = getArray(10,1,6, "Симуляция 1: ");
        ArrayList<Integer> sim2 = getArray(50,1,6, "Симуляция 2: ");
        ArrayList<Integer> sim3 = getArray(100,1,6, "Симуляция 3: ");

        System.out.println("Симуляция 1 (10 элементов):\nИгрок 1: " + getScore(player1, sim1) +"\nИгрок 2: " + getScore(player2, sim1));
        System.out.println("Симуляция 2 (50 элементов):\nИгрок 1: " + getScore(player1, sim2) +"\nИгрок 2: " + getScore(player2, sim2));
        System.out.println("Симуляция 3 (100 элементов):\nИгрок 1: " + getScore(player1, sim3) +"\nИгрок 2: " + getScore(player2, sim3));

        //подсчёт вероятности выигрыша
        System.out.println("\nСогласно теории вероятностей, если есть всего 3 варианта событий,\n" +
                "без влияния внешних факторов у них примерно равные значения - 33%\n" +
                "Это подтверждается следующим:");

        int NN = 10000000;
        int[] scores = {0, 0, 0};
        int score1, score2;
        for (int i = 0; i < NN; i++) {
            ArrayList<Integer> sim = getArray(100, 1, 6);
            score1 = getScore(player1, sim);
            score2 = getScore(player2, sim);
            if (score1 > score2)
                scores[0]++;
            else if (score2 > score1)
                scores[1]++;
            else
                scores[2]++;
        }
        System.out.printf("\tВыборка из %d симуляций:\n", NN);
        System.out.println("\tКоличество побед (1, 2, ничья): " + scores[0] + ", " + scores[1] + ", " + scores[2]);
        System.out.println("\t\tP1 = " + (float)scores[0]/NN);
        System.out.println("\t\tP2 = " + (float)scores[1]/NN);
        System.out.println("\t\tPdraw = " + (float)scores[2]/NN);
    }

    /**
    * 4. Написать программу распределения данного набора целых чисел (возможны повторяющиеся числа) на заданное число
     * групп так, чтобы были равными суммы чисел, входящих в каждую группу. Если это сделать невозможно, то программа
     * определяет этот факт. Вывести на экран исходные данные и результаты. Задачу необходимо решить точно: не
     * эвристический, а точный алгоритм.
     * Допустимо использовать полный перебор всех вариантов распределения чисел по группам.
     */
    private static void task4() {
        ArrayList<Integer> arr = getArray((new Random()).nextInt(20 - 1) + 1,1,20,"Исходный массив: ");
        System.out.print("На сколько групп разделить? ");
        int count = scanner.nextInt();

        int sum = 0;
        for(int i : arr)
            sum += i;
        int resultSum = sum / count;
        if(sum % count != 0){
            System.out.println("Невозможно разделить (1. сумма всех элементов не делится без остатка).");
        }
        else {
            boolean flag = true;
            //проходим по количеству требуемых сумм
            for (int i = 0; i < count; i++) {
                //собираем сумму, убираем из массива использованные элементы
                ArrayList<Integer> list = getNewListForTask(arr, resultSum, 0);
                if (list.get(0) == -1) {
                    Collections.reverse(arr);
                    list = getNewListForTask(arr, resultSum, 0);
                    if(list.get(0)==-1) {
                        System.out.println("Невозможно разделить (2. нельзя составить нужную комбинацию).");
                        flag = false;
                        break;
                    }
                }
                //удаляем задействованные числа из основеного массива
                for (int j : list)
                    for (int k = 0; k < arr.size(); k++)
                        if (j == arr.get(k)) {
                            arr.remove(k);
                            break;//чтоб не удаляло дубликаты
                        }

                System.out.println("\n" + list);
            }
            if (!flag)
                System.out.println("\tНезадействованные числа: " + arr);
        }
    }

    /**
    * Функция заполнения массива случайными элементами
     * @param N Количество элементов
     * @param MIN Минимальное значение элементов
     * @param MAX Максимальное значение элементов
     * @param msg Сообщение при выводе массива
     * @return Массив случайных чисел
     */
    private static ArrayList<Integer> getArray(int N, int MIN, int MAX, String msg) {
        ArrayList<Integer> arr = getArray(N,MIN,MAX);
        System.out.println(msg + arr);
        return arr;
    }

    /**
     * Функция заполнения массива случайными элементами
     * @param N Количество элементов
     * @param MIN Минимальное значение элементов
     * @param MAX Максимальное значение элементов
     * @return Массив случайных чисел
     */
    private static ArrayList<Integer> getArray(int N, int MIN, int MAX) {
        //заполнение массива случайными значениями и вывод на экран
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < N; i++)
            arr.add(random.nextInt(MAX - MIN) + MIN);
        return arr;
    }

    /**
     * Функция сортировки числового массива 'array' по возрастанию
     * @param array адрес исходного массива
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
     * @param array адрес исходного массива
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
     * Функция для поиска элемента
     * @param arr Массив поиска
     * @param x Искомый элемент
     * @return Результат поиска
     */
    private static boolean isUnique(int[][] arr, int x) {
        for(int i = 0; i < arr.length; i++)
            if(arr[i][0] == x)
                return false;
        return true;
    }

    /**
     * Функция получения индекса уникального элемента
     * @param arr Область поиска
     * @param x Искомое значение элемента
     * @return Индекс элемента
     */
    private static int getIndex(int[][] arr, int x) {
        for(int i = 0; i < arr.length; i++){
            if(arr[i][0] == x)
                return i;
        }
        return -1;
    }

    /**
     * Функция подсчёта результата игры
     * @param player Комбинация игрока
     * @param sim Массив для симуляции результатов игры
     * @return Количество заработанных очков
     */
    private static int getScore(ArrayList<Integer> player, ArrayList<Integer> sim) {
        int score = 0;
        int counter = 0;
        for(int i = 0; i < sim.size(); i++){
            if(player.get(counter) == sim.get(i)) {
                counter++;
            }
            else {
                if (player.get(0) == sim.get(i))
                    counter = 1;
                else
                    counter = 0;
            }
            if(counter == 3){
                counter = 0;
                score++;
            }
        }

        return score;
    }

    /**
     * Функция рекурсивного сбора суммы
     * @param array входящий массив элементов
     * @param res искомая сумма
     * @param lvl уровень вложенности (полезен для отладки)
     * @return массив с требуемой суммой либо -1
     */
    private static ArrayList<Integer> getNewListForTask(ArrayList<Integer> array, int res, int lvl){
        ArrayList<Integer> list = new ArrayList<>();
        boolean flag = false;//если не найден ответ, закончить обработку
        int i = 0;
        int s;
        while(i < array.size()) {
            int result = res - array.get(i);
            if (result > 0) {
                ArrayList<Integer> newList = (ArrayList<Integer>) array.clone();
                newList.remove(i);
                ArrayList<Integer> funcResult = getNewListForTask(newList, result, lvl + 1);
                if (funcResult.get(0) != -1) {
                    list.add(array.get(i));
                    list.addAll(funcResult);
                    flag = true;
                    break;
                }
            } else {
                if (result == 0) {
                    list.add(array.get(i));
                    flag = true;
                }
                break;
            }
            i++;
        }
        if(flag)
            return list;
        else
            return new ArrayList<Integer>(Arrays.asList(new Integer[]{-1}));
    }
}
