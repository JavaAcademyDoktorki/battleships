package com.battleships;

class PlayerCommand {
    private final Command type;
    private final String value;

    PlayerCommand(String playerInput) {
        // TODO validate it !
        String[] inputCommand = playerInput.split(CommunicatingProtocol.getSeparator());
        type = Command.valueOf(inputCommand[0]);
        value = inputCommand[1];
    }

    Command getType() {
        return type;
    }

    String getValue() {
        return value;
    }
}
