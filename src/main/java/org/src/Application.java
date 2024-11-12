package org.src;

import java.nio.file.*;
import java.util.Scanner;

public class Application {
    private static final Character[] ALPH = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
    };

    public static void main(String[] args) {
        System.out.println("Приветсвую, мой дорогой пользователь!");
        System.out.println("Это мой криптоанализатор");
        boolean bool_index = true;

        Scanner scanner = new Scanner(System.in);

        while (bool_index) {
            System.out.println("\nМеню:");
            System.out.println("1. Шифрование");
            System.out.println("2. Расшифровка с ключом");
            System.out.println("0. Выход");

            byte index_menu = scanner.nextByte();

            switch (index_menu) {
                case 1:
                    System.out.print("Введите путь к файлу: ");
                    String path = scanner.next();

                    // Проверка, существует ли файл
                    Path filePath = Paths.get(path);
                    if (!Files.exists(filePath)) {
                        System.out.println("Ошибка: файл не существует.");
                        break;
                    }

                    FileManager file = new FileManager();
                    String text = file.readFile(path);

                    System.out.print("Введите ключ: ");
                    int shift = scanner.nextInt();

                    while (shift == ALPH.length) {
                        System.out.print("Бесмыслица, введите другой ключ: ");
                        shift = scanner.nextInt();
                    }

                    String text_encrypt = Cipher.encrypt(text, shift);
                    file.writeFile(text_encrypt, path);
                    break;
                case 2:
                    System.out.print("Введите путь к файлу: ");
                    path = scanner.next();

                    filePath = Paths.get(path);
                    if (!Files.exists(filePath)) {
                        System.out.println("Ошибка: файл не существует.");
                        break;
                    }

                    file = new FileManager();
                    text = file.readFile(path);

                    System.out.print("Введите ключ: ");
                    shift = scanner.nextInt();

                    while (shift == ALPH.length) {
                        System.out.print("Бесмыслица, введите другой ключ: ");
                        shift = scanner.nextInt();
                    }

                    text_encrypt = Cipher.decrypt(text, shift);
                    file.writeFile(text_encrypt, path);
                    break;
                case 0:
                    bool_index = false;
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }

        scanner.close();
    }
}
