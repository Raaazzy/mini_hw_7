import java.util.Objects;

// Класс для игрока-человека, наследуется от PLayer
public class Human extends Player {
    // Конструктор, с параметром, который содержит имя игрока
    public Human(String name) {
        this.name = name;
        points = 0;
        color = "green";
    }

    // Переопределенный метод для совершения хода
    @Override
    public void doMove(String color, Table table) {
        // Запоминаем первоначальный вид поля
        table.copyField();
        // Если можно совершить ход, то запускаем алгоритм
        if (table.promptChops.isEmpty()) {
            return;
        }
        int x = table.chosenChip.getPosition()
                .getX() - 1;
        int y = table.chosenChip.getPosition()
                .getY() - 1;
        // Пробегаемся по всем направлениям вокруг выбранной фишки
        for (int delX = -1; delX < 2; ++delX) {
            for (int delY = -1; delY < 2; ++delY) {
                if (!(delX == 0 && delY == 0)) {
                    // Пока не дошли до конца поля или до пустой клетки или до фишки-подсказки, идем дальше
                    while (x + delX >= 0 && x + delX < 8 && y + delY >= 0 && y + delY < 8 && !table.field[x + delX][y + delY].isTransparent() && !table.field[x + delX][y + delY].isYellow()) {
                        x += delX;
                        y += delY;
                        // Мы пошли до фишки нашего цвета?
                        if (isItLastChip(table, x, y, delX, delY)) {
                            break;
                        }
                    }
                    x = table.chosenChip.getPosition()
                            .getX() - 1;
                    y = table.chosenChip.getPosition()
                            .getY() - 1;
                }
            }
        }
        table.clearPrompt();
        calculateChips(table);
    }

    // Проверяет, дошли ли мы до фишки нашего цвета
    public boolean isItLastChip(Table table, int x, int y, int delX, int delY) {
        // Если фишка нашего цвета, то запускаем алгоритм
        if ((table.field[x][y].isGreen() && Objects.equals(color, "green")) || table.field[x][y].isRed() && Objects.equals(color, "red")) {
            // Пока не дошли до первоначальной фишки, то красим все на своем пути в наш цвет
            while (!(x == table.chosenChip.getPosition()
                    .getX() - 1 && y == table.chosenChip.getPosition()
                    .getY() - 1)) {
                x -= delX;
                y -= delY;
                if (Objects.equals(color, "green")) {
                    table.field[x][y].makeGreen();
                } else {
                    table.field[x][y].makeRed();
                }
            }
            return true;
        }
        return false;
    }
}
