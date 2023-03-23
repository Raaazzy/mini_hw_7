import java.net.CookieHandler;
import java.util.Objects;

// Класс для Искусственного Интеллекта, наследуется от PLayer
public class Computer extends Player {

    // Хранит направление, в котором стоит закрашивать фишки
    private RadiusVector direction;

    // Хранит информацию о том, встречалась ли нам раннее красная фишка
    private boolean wasRedChip = false;

    private int maxPointsPerMove = 0;

    // Конструктор без парамметров, который присваивает значения по умолчанию
    public Computer() {
        name = "Computer";
        color = "red";
        points = 0;
        direction = new RadiusVector(0, 0);
    }

    // Переопределенный метод для совершения хода
    @Override
    public void doMove(String color, Table table) {
        // Запоминаем первоначальный вид поля
        table.copyField();
        // Высвечиваем все подсказки
        table.givePrompt(color);
        // Если можно совершить ход, то запускаем алгоритм
        if (table.promptChops.isEmpty()) {
            System.out.println("Игрок " + name + " пропускает ход");
            return;
        }
        Chip chipForMove = chipForMove = findBestEasyMove(color, table);
        int x = chipForMove.getPosition()
                .getX() - 1;
        int y = chipForMove.getPosition()
                .getY() - 1;
        // Совершаем лучший ход, закрашивая фишки
        while ((!table.field[x][y].isRed() && Objects.equals(color, "red")) || (!table.field[x][y].isGreen() && Objects.equals(color, "green"))) {
            if (Objects.equals(color, "red")) {
                table.field[x][y].makeRed();
            } else {
                table.field[x][y].makeGreen();
            }
            x += direction.getX();
            y += direction.getY();
        }
        // Очищаем подсказки
        table.clearPrompt();
        // Считаем количество всех фишек на поле
        calculateChips(table);
    }

    // Находим лучший возможный легкий ход
    Chip findBestEasyMove(String color, Table table) {
        // Наибольшее количество очков при совершении хода
        maxPointsPerMove = 0;
        // Наилучшая фишка для совершении хода
        Chip bestChipForMove = table.promptChops.get(0);
        // Перебираем все фишки, которыми возможно сделать ход
        for (Chip possibleChip : table.promptChops) {
            int x = possibleChip.getPosition()
                    .getX() - 1;
            int y = possibleChip.getPosition()
                    .getY() - 1;
            // Пробегаемся во всем направлениям вокруг этих фишек
            for (int delX = -1; delX < 2; ++delX) {
                for (int delY = -1; delY < 2; ++delY) {
                    wasRedChip = false;
                    if (!(delX == 0 && delY == 0)) {
                        // Если мы нашли зеленую или красную фишку, то продолжаем двигаться в определенном направлении
                        while (x + delX >= 0 && x + delX < 8 && y + delY >= 0 && y + delY < 8 && !table.field[x + delX][y + delY].isTransparent() && !table.field[x + delX][y + delY].isYellow()) {
                            x += delX;
                            y += delY;
                            // Если это фишка красного цвета (при условии, что компютер красный) или зеленого (иначе), то считаем
                            // очки, которые получим при совершении данного хода
                            int currentPoints = isRedChip(table, new Coordinates(x + 1, y + 1), possibleChip, delX, delY);
                            // Проверяем этот ход на максимум очков и лучшую фишку
                            if (currentPoints > 0 && currentPoints > maxPointsPerMove) {
                                maxPointsPerMove = currentPoints;
                                bestChipForMove = possibleChip;
                                direction = new RadiusVector(delX, delY);
                                break;
                            }
                            // Если уже встречали нашу фишку по дороге, то прекращаем алгоритм
                            if (wasRedChip) {
                                break;
                            }
                        }
                        // Возвращаем первоначальные координаты фишки
                        x = possibleChip.getPosition()
                                .getX() - 1;
                        y = possibleChip.getPosition()
                                .getY() - 1;
                    }
                }
            }
        }
        return bestChipForMove;
    }

    // Проверяем - это фишка противоположного цвета?
    int isRedChip(Table table, Coordinates chipPosition, Chip possibleChip, int delX, int delY) {
        int x = chipPosition.getX() - 1;
        int y = chipPosition.getY() - 1;
        int currentPoints = -1;
        // Если фишка нашего цвета, то запускаем алгоритм
        if (table.field[x][y].isRed() && Objects.equals(color, "red") || table.field[x][y].isGreen() && Objects.equals(color, "green")) {
            wasRedChip = true;
            currentPoints = 0;
            // В зависимости от положения фишки, начисляем ей определенного количество очков
            if ((x == 0 && y == 0) || (x == 0 && y == 7) || (x == 7 && y == 0) || (x == 7 && y == 7)) {
                currentPoints += 0.8;
            } else if (x == 0 || x == 7 || y == 0 || y == 7) {
                currentPoints += 0.4;
            }
            // Пока не дошли до первоначальной фишки, считаем количество очков
            while (!(x - delX == possibleChip.getPosition()
                    .getX() - 1 && y - delY == possibleChip.getPosition()
                    .getY() - 1)) {
                x -= delX;
                y -= delY;
                if (x == 0 || x == 7 || y == 0 || y == 7) {
                    currentPoints += 2;
                } else {
                    ++currentPoints;
                }
            }
        }
        // Вовращаем количество очков
        return currentPoints;
    }
}
