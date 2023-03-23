import java.util.ArrayList;
import java.util.List;

// Класс, хранящий информацию о всех игроках в данной сессии
public class Players {
    // Все игроки сессии
    private List<Player> playersPerSession;

    // Первый игрок, играющий зелеными фишками
    private Player firstPlayer;

    // Индекс первого игрока в списке игроков
    private int firstPlayerIndex;

    // Второй игрок, играющий красными фишками
    private Player secondPlayer;

    // Индекс второго игрока в списке игроков
    private int secondPlayerIndex;

    // Конструктор, принимающий игроков, и задающий первоначальные значения полям
    Players(Player player) {
        firstPlayerIndex = -1;
        secondPlayerIndex = -1;
        playersPerSession = new ArrayList<Player>();
        playersPerSession.add(player);
        firstPlayer = new Computer();
        secondPlayer = new Computer();
        firstPlayer.changeColor("green");
    }

    // Вернуть индекс первого игрока
    public int getFirstPlayerIndex() {
        return firstPlayerIndex;
    }

    // Вернуть индекс второго игрока
    public int getSecondPlayerIndex() {
        return secondPlayerIndex;
    }

    // Вернуть количество игроков в сессии
    public int getCount() {
        return playersPerSession.size();
    }

    // Вернуть игрока по индексу
    public Player get(int number) {
        return playersPerSession.get(number);
    }

    // Выбрать игрока для игры
    public void chooseHuman(int index, int numberOfPlayer) {
        // Если выбран первый игрок, то присваиваем его первому игроку, иначе - второму
        if (numberOfPlayer == 1) {
            firstPlayer = playersPerSession.get(index);
            firstPlayerIndex = index;
        } else {
            secondPlayer = playersPerSession.get(index);
            secondPlayerIndex = index;
        }
        // Меняем цвета у игроков на соотвествующие
        firstPlayer.changeColor("green");
        secondPlayer.changeColor("red");
    }

    // Выбрать компьютер для игры
    public void chooseComputer(int numberOfPlayer) {
        // Если выбрать первый игрок, то присваиваем ему компьютер, иначе - второму
        if (numberOfPlayer == 1) {
            firstPlayer = new Computer();
        } else {
            secondPlayer = new Computer();
        }
        firstPlayer.changeColor("green");
        secondPlayer.changeColor("red");
    }

    // Вернуть первого игрока
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    // Вернуть первого игрока
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void add(Human newPlayer) {
        playersPerSession.add(newPlayer);
    }

    // Переопределяем метод для вывода информации о игроках
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Player player : playersPerSession) {
            out.append(player.name)
                    .append(": ")
                    .append(player.points)
                    .append("\n");
        }
        return out.toString();
    }

    // Создаем нового игрока
    public void createNewPlayer(String name) {
        playersPerSession.add(new Human(name));
    }
}
