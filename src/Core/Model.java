package Core;

import Core.Exceptions.*;
import Figures.Figure;
import Game.Field;
import Util.Position;

import java.util.HashMap;
import java.util.Map;

public class Model {

    public enum Message {
        ERROR_NO_FIGURE,
        ERROR_FIGURE_CANT_MOVE,
        ERROR_INPUT_POSITION,
        ERROR_INPUT_POSITION_DONT_HAVE_SPLITTER,
        ERROR_INPUT_POSITION_HAVE_INCORRECT_VALUES,
        MESSAGE_START_GAME,
        MESSAGE_GET_POSITION,
        MESSAGE_CURRENT_COLOR_WHITE,
        MESSAGE_CURRENT_COLOR_BLACK,
        MESSAGE_CURRENT_COLOR_LOSE;

        private static String get(Message messageName) {
            switch (messageName) {
                case MESSAGE_START_GAME:
                    return "Game started";

                case ERROR_NO_FIGURE:
                    return "There is no figure on this position";

                case MESSAGE_GET_POSITION:
                    return "Write your move: {char num}";

                case ERROR_FIGURE_CANT_MOVE:
                    return "Figure cant move";

                case ERROR_INPUT_POSITION:
                    return "Incorrect position. Try again.";

                case ERROR_INPUT_POSITION_DONT_HAVE_SPLITTER:
                    return "Input position don`t have spiltter. Use space as splitter.";

                case ERROR_INPUT_POSITION_HAVE_INCORRECT_VALUES:
                    return "Coordinates must have one char. Example: \"B 3\"";

                case MESSAGE_CURRENT_COLOR_WHITE:
                    return "White`s move";

                case MESSAGE_CURRENT_COLOR_BLACK:
                    return "Black`s move";

                case MESSAGE_CURRENT_COLOR_LOSE:
                    return "Current color lose! GAME OVER.";

                default:
                    return null;
            }
        }
    }

    static private Map<Character, Short> wordToNumber;

    private Field field;

    private static class Holder {
        static final Model instance = new Model();

    }

    public static Model getInstance() {
        return Holder.instance;
    }

    private Model() {

    }

    static {
        wordToNumber = new HashMap<>();
        wordToNumber.put('A', (short) 1);
        wordToNumber.put('B', (short) 2);
        wordToNumber.put('C', (short) 3);
        wordToNumber.put('D', (short) 4);
        wordToNumber.put('E', (short) 5);
        wordToNumber.put('F', (short) 6);
        wordToNumber.put('G', (short) 7);
        wordToNumber.put('H', (short) 8);


    }

    void init(final short width, final short height) {
        field = Field.getInstance();
        field.init(width, height);
    }

    String getMessage(Message message) {
        return Message.get(message);
    }

    void move(Position start, Position end) throws FigureIsNullException, FigureCantMoveException, KingCantMoveException {

        final Figure currentFigure = field.getFigureByPosition(start);

        if (currentFigure == null) {
            throw new FigureIsNullException();
        }

        if (!currentFigure.canMove(end)) {
            throw new FigureCantMoveException();
        }

        final Figure figureByPositionOnEnd = field.getFigureByPosition(end);
        if (figureByPositionOnEnd != null) {
            figureByPositionOnEnd.setDead();
        }

        currentFigure.move(end);
    }

    public boolean isPositionBusy(Position position) {
        return field.isPositionBusy(position);
    }

    public Position parseStringWithPosition(String string) throws InputPositionDontHaveSplitterException, InputPositionHaveIncorrectValuesException {
        String splitter = " ";
        if (!string.contains(splitter)) {
            throw new InputPositionDontHaveSplitterException();
        }

        String[] strings = string.split(splitter);

        if (strings[0].length() != 1 && strings[1].length() != 1) {
            throw new InputPositionHaveIncorrectValuesException();
        }

        Character character = strings[0].charAt(0);
        Short secondNum = Short.parseShort(strings[1]);
        if(secondNum < 1 || secondNum > 8){
            throw new InputPositionHaveIncorrectValuesException();
        }

        Short firstNum = wordToNumber.get(Character.toUpperCase(character));
        if (firstNum == null){
            throw new InputPositionHaveIncorrectValuesException();
        }

        return new Position(firstNum, secondNum);
    }

}
