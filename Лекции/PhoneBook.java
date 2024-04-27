package Лекции;

import java.util.*;

public class PhoneBook {
    private HashMap<String, List<String>> contacts;
    private Scanner scanner;

    public PhoneBook() {
        this.contacts = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    // Метод для добавления контакта в телефонную книгу
    public void addContact(String name, String phoneNumber) {
        List<String> numbers = contacts.getOrDefault(name, new ArrayList<>());
        numbers.add(phoneNumber);
        contacts.put(name, numbers);
    }

    // Метод для поиска номера по имени
    public void findContact(String name) {
        if (contacts.containsKey(name)) {
            List<String> numbers = contacts.get(name);
            System.out.println("Найденные номера для " + name + ": " + numbers);
        } else {
            System.out.println("Контакт с именем " + name + " не найден.");
        }
    }

    // Метод для удаления контакта по имени
    public void deleteContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Контакт с именем " + name + " успешно удален.");
        } else {
            System.out.println("Контакт с именем " + name + " не найден.");
        }
    }

    // Метод для изменения номера контакта
    public void updateContact(String name, String phoneNumber) {
        if (contacts.containsKey(name)) {
            List<String> numbers = contacts.get(name);
            System.out.println("Хотите изменить действующий номер или добавить новый?");
            System.out.println("1 - Изменить действующий номер");
            System.out.println("2 - Добавить новый номер");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки после считывания числа
            if (choice == 1) {
                if (!numbers.isEmpty()) {
                    System.out.println("Текущие номера для " + name + ": " + numbers);
                    System.out.println("Введите старый номер, который вы хотите изменить:");
                    String oldPhoneNumber = scanner.nextLine();
                    if (numbers.contains(oldPhoneNumber)) {
                        System.out.println("Введите новый номер:");
                        String newPhoneNumber = scanner.nextLine();
                        numbers.remove(oldPhoneNumber);
                        numbers.add(newPhoneNumber);
                        System.out.println("Номер для контакта " + name + " успешно изменен.");
                    } else {
                        System.out.println("Номер " + oldPhoneNumber + " для контакта " + name + " не найден.");
                    }
                } else {
                    System.out.println("Нет текущих номеров для " + name + ". Добавьте новый номер.");
                }
            } else if (choice == 2) {
                System.out.println("Введите новый номер:");
                String newPhoneNumber = scanner.nextLine();
                numbers.add(newPhoneNumber);
                System.out.println("Номер для контакта " + name + " успешно добавлен.");
            } else {
                System.out.println("Неверный выбор. Пожалуйста, выберите 1 или 2.");
            }
        } else {
            System.out.println("Контакт с именем " + name + " не найден.");
        }
    }

    // Метод для запуска программы
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Поиск номера");
            System.out.println("2 - Добавление нового контакта");
            System.out.println("3 - Удаление контакта");
            System.out.println("4 - Изменение номера");
            System.out.println("0 - Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки после считывания числа

            switch (choice) {
                case 1:
                    System.out.println("Введите имя для поиска:");
                    String nameToFind = scanner.nextLine();
                    findContact(nameToFind);
                    break;
                case 2:
                    System.out.println("Введите имя нового контакта:");
                    String newName = scanner.nextLine();
                    System.out.println("Введите номер нового контакта:");
                    String newPhoneNumber = scanner.nextLine();
                    addContact(newName, newPhoneNumber);
                    System.out.println("Контакт " + newName + " успешно добавлен.");
                    break;
                case 3:
                    System.out.println("Введите имя контакта для удаления:");
                    String nameToDelete = scanner.nextLine();
                    deleteContact(nameToDelete);
                    break;
                case 4:
                    System.out.println("Введите имя контакта для изменения номера:");
                    String nameToUpdate = scanner.nextLine();
                    System.out.println("Введите новый номер:");
                    String newPhoneNumberToUpdate = scanner.nextLine();
                    updateContact(nameToUpdate, newPhoneNumberToUpdate);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, введите корректное число.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.run();
    }
}