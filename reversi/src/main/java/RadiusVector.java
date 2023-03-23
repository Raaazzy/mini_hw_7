import java.util.Objects;

// Класс для указания направления фишки
public class RadiusVector {

    // Координата Х
    private final int x;

    // Координата Y
    private final int y;

    // Конструктор для инициализации координат
    RadiusVector(int x, int y) {
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
}
