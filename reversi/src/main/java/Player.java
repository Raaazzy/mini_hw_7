import java.util.Objects;

// Абстрактный класс PLayer, определящий функционал Human и Computer
public abstract class Player {

    // Цвет игрока
    protected String color;

    // Имя игрока
    protected String name;

    // Очки игрока
    protected int points;

    // Метод для совершения хода
    public abstract void doMove(String color, Table table);

    // Возвращает очки
    final public int getPoints() {
        return points;
    }

    // Возвращает цвет фишек игрока
    final public String getColor() {
        return color;
    }

    // Меняет цвет фишек игрока
    final public void changeColor(String color) {
        this.color = color;
    }

    // Возвращает имя игрока
    public String getName() {
        return name;
    }

    // Считает количество фишек на поле
    final public void calculateChips(Table table) {
        ++table.countOfChipsOnField;
        table.countOfRedChipsOnField = 0;
        table.countOfGreenChipsOnField = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (table.field[i][j].isRed()) {
                    ++table.countOfRedChipsOnField;
                } else if (table.field[i][j].isGreen()) {
                    ++table.countOfGreenChipsOnField;
                }
            }
        }
    }

    final public void changeOnMaxPoints(int points) {
        if (points > this.points) {
            this.points = points;
        }
    }
}
