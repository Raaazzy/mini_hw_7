import java.util.Objects;

// Класс коорлдинат фишек
public class Coordinates {

    // Координата Х
    private final int x;

    // Координата Y
    private final int y;

    // Конструктор для инициализации координат
    Coordinates(int x, int y) {
        if (x < 1 || x > 8 || y < 1 || y > 8) {
            throw new IllegalArgumentException("Вы ввели неверные координаты. Пожалуйста, попробуйте еще раз:");
        }
        this.x = x;
        this.y = y;
    }

    // Возвращает Х
    public int getX() {
        return x;
    }

    // Возвращает Y
    public int getY() {
        return y;
    }

    // Переопределенный equals для сравнивания фишек
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    // Переопределенный hashCode
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
