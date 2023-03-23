import java.util.Objects;
import java.util.Scanner;

public class Main {

    // Игроки сессии
    static Players playersPerSession;

    // Поле
    static Table table;

    public static void main(String[] args) {
        playersPerSession = new Players(new Human("Player"));
        // Пока пользователь не захочет завершить сессию - играем
        while (true) {
            System.out.println("Добро пожаловать в игру реверси!");
            System.out.println("Нажмите на \"1\" для завершения сеанса и на любой другой символ для продолжения:");
            Scanner in = new Scanner(System.in);
            // Если ввели "1", то завершаем сессию
            if (Objects.equals(in.next(), "1")) {
                break;
            }
            System.out.println("Выберите режим игры: ");
            System.out.println("1. Легкий");
            System.out.println("2. Продвинутый");
            System.out.println("3. Игрок против игрока");
            int numberOfPlayer;
            switch (chooseSmth(1, 3)) {
                case 1:
                    System.out.println("Выберите цвет, которым будете играть: ");
                    System.out.println("1. Зеленый");
                    System.out.println("2. Красный");
                    // Выбираем цвет
                    numberOfPlayer = chooseSmth(1, 2);
                    // Выбираем имя игрока
                    choosePlayerName(numberOfPlayer);
                    // Выбинарем цвет для компьютера
                    playersPerSession.chooseComputer((numberOfPlayer % 2) + 1);
                    // Запускаем режим легкого копьютера
                    easyComputerMode();
                    messageOfWin();
                    break;
                case 2:
                    hardComputerMode();
                    break;
                case 3:
                    // Выбираем имя игрока
                    choosePlayerName(1);
                    while (true) {
                        // Выбираем имя игрока
                        choosePlayerName(2);
                        if (playersPerSession.getSecondPlayerIndex() == -1 || playersPerSession.getFirstPlayerIndex() == playersPerSession.getSecondPlayerIndex()) {
                            System.out.println("Вы не можете играть за того же персонажа, что и игрок 1. Попробуйте еще раз: ");
                        } else {
                            break;
                        }
                    }
                    // Запускаем режим для двух игроков
                    twoPlayerMode();
                    messageOfWin();
                    break;
            }
        }
        finishGameSession();
    }

    // Метод для завершения игры с выводм очков игроков
    static void finishGameSession() {
        System.out.println("Благодарим Вас за сногшибательную игру!");
        System.out.println("Лучшие результаты игроков за сессию:");
        System.out.println(playersPerSession);
    }

    // Метод для выбора с помощью чисел чего-нибудь
    static int chooseSmth(int firstRestrictions, int secondRestrictions) {
        int number = 0;
        boolean flag = true;
        // Пока не введем корректные данные - продолжаем вводить
        while (flag) {
            try {
                Scanner in = new Scanner(System.in);
                number = in.nextInt();
                if (number >= firstRestrictions && number <= secondRestrictions) {
                    flag = false;
                } else {
                    System.out.print("Вы ввели некорректное значение. Попробуйте еще раз: ");
                }
            } catch (Exception e) {
                System.out.print("Вы ввели некорректное значение. Попробуйте еще раз: ");
            }
        }
        System.out.println();
        // Возвращаем выбранную цифру
        return number;
    }

    // Метод для ввода координат
    static void inputCoordinates(Table table, Human player) {
        // Если на поле есть подсказки, то значит есть и доступные ходы, а значит выполняем алгоритм
        if (!table.promptChops.isEmpty()) {
            int x, y;
            boolean flag = true;
            // Пока не выбрали корректные координаты, алгоритм не прекращаем
            while (flag) {
                System.out.println("Если вы хотите отменить ход, то введите значения для хода: Х = 0 и Y = 0");
                System.out.print("Введите координаты X и Y вашего хода: ");
                try {
                    Scanner in = new Scanner(System.in);
                    y = in.nextInt();
                    x = in.nextInt();
                    // Если ввели 0 0, то нужно откатить ход назад, иначе пробуем сделать ход
                    if (x == 0 && y == 0) {
                        if (table.doUndoMove()) {
                            table.clearPrompt();
                            table.givePrompt(player.getColor());
                            System.out.print(table);
                        }
                    } else {
                        Coordinates coordinates = new Coordinates(x, y);
                        // Если мы выбрали ячейку с подсказкой, то делаем ход
                        if (table.isPrompt(coordinates)) {
                            player.doMove(player.getColor(), table);
                            flag = false;
                        } else {
                            System.out.print("Вы ввели некорректное значение. Попробуйте еще раз: ");
                        }
                    }
                } catch (Exception e) {
                    System.out.print("Вы ввели некорректное значение. Попробуйте еще раз: ");
                }
            }
        } else {
            System.out.println("Игрок " + player.name + " пропускает ход");
        }
    }

    // Метод для выбра имени игрока
    static void choosePlayerName(int playerNumber) {
        System.out.println("Выберите собственного игрока среди представленных или создайте нового:");
        System.out.println("1. Создать нового");
        // Выводи список всех существующих игроков
        for (int i = 0; i < playersPerSession.getCount(); ++i) {
            System.out.println((i + 2) + ". " + playersPerSession.get(i)
                    .getName());
        }
        // Даем пользователю выбрать игрока
        int playerIndex = chooseSmth(1, 1 + playersPerSession.getCount());
        // Если выбрал 1, то нужно создать нового игрока, иначе - выбираем существующего
        if (playerIndex == 1) {
            System.out.print("Введите имя персонажа: ");
            Scanner in = new Scanner(System.in);
            playersPerSession.createNewPlayer(in.next());
            playersPerSession.chooseHuman(playersPerSession.getCount() - 1, playerNumber);
        } else {
            playersPerSession.chooseHuman(playerIndex - 2, playerNumber);
        }
    }

    // Метод для вывода сообщения о победе
    static public void messageOfWin() {
        if (table.countOfGreenChipsOnField > table.countOfRedChipsOnField) {
            playersPerSession.getFirstPlayer()
                    .changeOnMaxPoints(table.countOfGreenChipsOnField);
            System.out.println("Победил игрок " + playersPerSession.getFirstPlayer().name + "!");
        } else if (table.countOfGreenChipsOnField < table.countOfRedChipsOnField) {
            playersPerSession.getSecondPlayer()
                    .changeOnMaxPoints(table.countOfRedChipsOnField);
            System.out.println("Победил игрок " + playersPerSession.getSecondPlayer().name + "!");
        } else {
            playersPerSession.getSecondPlayer()
                    .changeOnMaxPoints(table.countOfRedChipsOnField);
            playersPerSession.getFirstPlayer()
                    .changeOnMaxPoints(table.countOfGreenChipsOnField);
            System.out.println("Ничья!");
        }
        System.out.println();

    }

    // Метод для режима игры двух игроков
    static public void twoPlayerMode() {
        table = new Table();
        while (!table.isFieldFull() && table.countOfGreenChipsOnField != 0 && table.countOfRedChipsOnField != 0) {
            if (playersPerSession.getFirstPlayer() instanceof Human fisrtPlayer && playersPerSession.getSecondPlayer() instanceof Human secondPlayer) {
                // Первый игрок делает ход
                table.givePrompt(fisrtPlayer.getColor());
                System.out.print(table);
                inputCoordinates(table, fisrtPlayer);
                // Второй игрок делает ход
                table.givePrompt(secondPlayer.getColor());
                System.out.print(table);
                inputCoordinates(table, secondPlayer);
            }
        }
    }

    // Метод для режима игры с легким компьютером
    static void easyComputerMode() {
        table = new Table();
        while (!table.isFieldFull() && table.countOfGreenChipsOnField != 0 && table.countOfRedChipsOnField != 0) {
            // Проверяем, кто является первым игроком: человек или компьютер
            if (playersPerSession.getFirstPlayer() instanceof Human fisrtPlayer) {
                // Человек делает ход
                table.givePrompt(playersPerSession.getFirstPlayer()
                        .getColor());
                System.out.print(table);
                inputCoordinates(table, fisrtPlayer);
                // Компьютер делает ход
                System.out.print(table);
                playersPerSession.getSecondPlayer()
                        .doMove(playersPerSession.getSecondPlayer().color, table);
            } else if (playersPerSession.getSecondPlayer() instanceof Human secondPlayer) {
                // Компьютер делает ход
                System.out.print(table);
                playersPerSession.getFirstPlayer()
                        .doMove(playersPerSession.getFirstPlayer().color, table);
                // Человек делает ход
                table.givePrompt(playersPerSession.getSecondPlayer()
                        .getColor());
                System.out.print(table);
                inputCoordinates(table, secondPlayer);
            }
        }
    }

    // Метод для режима игры со сложным компьютером
    static void hardComputerMode() {
        System.out.println("Данный режим находится в доработке. Увы, но сыграть в него Вы не сможете :(");
    }
}