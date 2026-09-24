import java.io.IOException;
import java.lang.ProcessBuilder;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите сколько процессов вы хотите запустить: ");
        int hmproc = sc.nextInt();
        sc.nextLine();
        System.out.println();
        for (int i = 1; i <= hmproc; i++) {
            System.out.println("Процесс №" + i);
            System.out.print("Введите имя программы (например, notepad, calc, mspaint): ");
            String program = sc.nextLine();

            try {
                Process process = new ProcessBuilder(program).start();

                ProcessHandle handle = process.toHandle();
                ProcessHandle.Info info = handle.info();

                System.out.println("\nПроцесс запущен");
                System.out.println("PID: " + handle.pid());
                System.out.println("Путь: " + info.command().orElse("нет данных"));

                System.out.println("Аргументы: " + info.arguments().map(Object::toString).orElse("нет"));
                System.out.println("Время запуска: " + info.startInstant().map(Object::toString).orElse("нет данных"));
                System.out.println("Пользователь: " + info.user().orElse("нет данных"));

                System.out.print("\nЗавершить д/н: ");
                String complete = sc.nextLine();

                if (complete.equalsIgnoreCase("д") ||
                        complete.equalsIgnoreCase("да")) {

                    if (handle.destroy()) {
                        System.out.println("Процесс завершён.");
                    } else {
                        System.out.println("Не удалось завершить процесс.");
                    }
                } else {
                    System.out.println("Процесс оставлен работать.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка запуска программы: " + e.getMessage());
            }
            System.out.println();
        }
        sc.close();
    }
}