import java.util.Objects;

// Класс игровых фигур
public class Chip {

    // Инициализация цветов для игровых фигур
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // поле цвета фигурки
    private String color;

    // поле координаты фигурки
    private final Coordinates position;

    // Констркутор, задающий координаты и цвет фишки
    Chip(String color, int x, int y) {
        if (Objects.equals(color, "red")) {
            this.color = "red";
        } else if (Objects.equals(color, "green")) {
            this.color = "green";
        } else if (Objects.equals(color, "yellow")) {
            this.color = "yellow";
        } else {
            this.color = "transparent";
        }
        position = new Coordinates(x, y);
    }

    // Конкруктор копирования
    public Chip(Chip chip) {
        this.color = chip.color;
        this.position = chip.position;
    }

    // Поле пустое?
    public boolean isTransparent() {
        return Objects.equals(color, "transparent");
    }

    // Сделай поле пустым
    public void makeTransparent() {
        color = "transparent";
    }

    // Поле желтое?
    public boolean isYellow() {
        return Objects.equals(color, "yellow");
    }

    // Сделай поле желтным
    public void makeYellow() {
        color = "yellow";
    }

    // Поле красное?
    public boolean isRed() {
        return Objects.equals(color, "red");
    }

    // Сделай поле красным
    public void makeRed() {
        color = "red";
    }

    // Поле зеленое?
    public boolean isGreen() {
        return Objects.equals(color, "green");
    }

    // Сделай поле зеленым
    public void makeGreen() {
        color = "green";
    }

    // Верни позицию фишки
    public Coordinates getPosition() {
        return position;
    }

    // Переопределенный метод дял вывода фишек
    @Override
    public String toString() {
        if (isTransparent()) {
            return " ";
        }
        if (isYellow()) {
            return ANSI_YELLOW + "●" + ANSI_RESET;
        }
        return isRed() ? (ANSI_RED + "●" + ANSI_RESET) : (ANSI_GREEN + "●" + ANSI_RESET);
    }
}
