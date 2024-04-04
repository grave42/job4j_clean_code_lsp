package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {

    public static Menu menu = new SimpleMenu();
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String rootEl = null;
        Printer printer = new Printer();
        do {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить элемент в корень меню");
            System.out.println("2. Добавить элемент к родительскому элементу");
            System.out.println("3. Вызвать действие, привязанное к пункту меню");
            System.out.println("4. Вывести меню в консоль.");
            System.out.println("5. Выход");
            System.out.print("Ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Введите название корневого элемента:");
                    String response = scanner.nextLine();
                    rootEl = response;
                    menu.add(Menu.ROOT, response, DEFAULT_ACTION);
                    break;
                case 2:
                    System.out.println("Введите название дочернего элемента:");
                    String response2 = scanner.nextLine();
                    menu.add(rootEl, response2, DEFAULT_ACTION);
                    break;
                case 3:
                    System.out.println("Введите имя пункта меню для действия:");
                    String response3 = scanner.nextLine();
                    ActionDelegate doAction = menu.select(response3).get().getActionDelegate();
                    doAction.delegate();
                    break;
                case 4:
                    System.out.println("Меню");
                    printer.print(menu);
                    break;
                case 5:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Пожалуйста, выберите действие из списка.");
                    break;
            }
        } while (choice != 5);
        scanner.close();
    }

}

