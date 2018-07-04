package com.battleships;

class PlayerCommand {
    private final Commands type;
    private final String value;

    PlayerCommand(String playerInput) {
        // TODO validate it !
        String[] inputCommand = playerInput.split(Commands.getSeparator());
        type = Commands.valueOf(inputCommand[0]);
        value = inputCommand[1];
    }

    Commands getType() {
        return type;
    }

    String getValue() {
        return value;
    }
}
