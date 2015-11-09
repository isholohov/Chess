package Game;

import Core.Controller;

public class Launcher {
    private final static short WIDTH = 8;
    private final static short HEIGHT = 8;
    //public final static Field field = new Field();

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        controller.init(WIDTH, HEIGHT);
        controller.start();

    }
}
