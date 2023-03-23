import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Класс игрового поля
public class Table {
    // история ходов
    public ArrayList<Chip[][]> moveHistory;

    // Список с фишками-подсказками
    public ArrayList<Chip> promptChops;

    // расстановка фигур на текущем поле
    public Chip[][] field;

    // Общее количество фишек на поле
    public int countOfChipsOnField;

    // Количество красных фишек на поле
    public int countOfRedChipsOnField;

    // Количество зеленых фишек на поле
    public int countOfGreenChipsOnField;

    // Выбранная игроком фишка
    public Chip chosenChip;

    // Конструктор без параметров, задающий начальный значения полям
    Table() {
        field = new Chip[8][8];
        initializeField();
        moveHistory = new ArrayList<Chip[][]>();
        promptChops = new ArrayList<Chip>();
        countOfChipsOnField = 4;
        countOfRedChipsOnField = 2;
        countOfGreenChipsOnField = 2;
    }

    // Поле заполнено?
    public boolean isFieldFull() {
        return countOfChipsOnField == 64;
    }

    // Инициализируем поле
    void initializeField() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                field[i][j] = new Chip("transparent", i + 1, j + 1);
            }
        }
        field[3][3] = (new Chip("red", 4, 4));
        field[3][4] = (new Chip("green", 5, 4));
        field[4][3] = (new Chip("green", 4, 5));
        field[4][4] = (new Chip("red", 5, 5));
    }

    // Переопределенный метод для вывода поля
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        String header = "  | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |";
        String border = "--+---+---+---+---+---+---+---+---+";
        out.append("\n")
                .append(header)
                .append("\n")
                .append(border)
                .append("\n");
        for (int i = 0; i < 8; ++i) {
            out.append(i + 1)
                    .append(" | ");
            for (int j = 0; j < 8; ++j) {
                out.append(field[i][j])
                        .append(" | ");
            }
            out.append("\n")
                    .append(border)
                    .append("\n");
        }
        out.append("\n");
        return out.toString();
    }

    // Возможно ли сделать зод сюда?
    public boolean isPossibleToMove(int x, int y, int delX, int delY, String color) {
        // Если мы за рамками поля, то точно нет
        if (x + delX < 0 || x + delX >= 8 || y + delY < 0 || y + delY >= 8 || (delX == 0 && delY == 0)) {
            return false;
        } else {
            // Пока не дошли до пустой или желтой клетки, или не вышли за рамки поля, то двигаем фишку
            while (x - delX >= 0 && x - delX < 8 && y - delY >= 0 && y - delY < 8 && !field[x - delX][y - delY].isTransparent() && !field[x - delX][y - delY].isYellow()) {
                x -= delX;
                y -= delY;
                // Если мы дошли до фишки своего цвета, то данный ход сделать возможно
                if (((field[x][y].isGreen() && Objects.equals(color, "green")) || (field[x][y].isRed() && Objects.equals(color, "red")))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Метод выводит подсказки на поле
    void givePrompt(String color) {
        // Пробегаем по всему полю
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                // Если нашли фишку противоположного цвета, то запускаем алгоритм
                if ((field[i][j].isRed() && Objects.equals(color, "green")) || field[i][j].isGreen() && Objects.equals(color, "red")) {
                    // Проверяем все фишки вокруг данной
                    for (int k = -1; k < 2; ++k) {
                        for (int l = -1; l < 2; ++l) {
                            // Если на нее возможно сделать ход, то красим в желтый и обавляем в список подсказок
                            if ((j + l) < 8 && (j + l) >= 0 && (i + k) < 8 && (i + k) >= 0 && field[i + k][j + l].isTransparent() && isPossibleToMove(i, j, k, l, color)) {
                                field[i + k][j + l].makeYellow();
                                promptChops.add(field[i + k][j + l]);
                            }
                        }
                    }
                }
            }
        }
    }

    // Проверяет, выбранная фишка является подсказкой или нет
    boolean isPrompt(Coordinates coordinates) {
        for (Chip promptChop : promptChops) {
            if (coordinates.equals(promptChop.getPosition())) {
                chosenChip = promptChop;
                return true;
            }
        }
        return false;
    }

    // Очищает подсказки
    void clearPrompt() {
        promptChops = new ArrayList<Chip>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (field[i][j].isYellow()) {
                    field[i][j].makeTransparent();
                }
            }
        }
    }

    // Делает ход назад
    public boolean doUndoMove() {
        // Если в истории зодов меньше 2 ходов, то сделать ход назад невозможно
        if (moveHistory.size() < 2) {
            System.out.print("Невозможно вернуть то, чего еще нет. Попробуйте еще раз: ");
            return false;
        }
        // Удаляем последний сделанный ход
        moveHistory.remove(moveHistory.size() - 1);
        // Присваиваем игроку предполсдений ход
        field = new Chip[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                field[i][j] = new Chip(moveHistory.get(moveHistory.size() - 1)[i][j]);
            }
        }
        // Удаляем предполсдений сделанный ход
        moveHistory.remove(moveHistory.size() - 1);
        return true;
    }

    // Метод для покирования состояния поля
    public void copyField() {
        moveHistory.add(new Chip[8][8]);
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                moveHistory.get(moveHistory.size() - 1)[i][j] = new Chip(field[i][j]);
            }
        }
    }
}
