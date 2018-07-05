package com.battleships;

import java.util.Optional;

class PlayerCommand {
    private final Command type;
    private Optional<String> valueOptional;

    PlayerCommand(String playerInput) {
        // TODO validate it !
        String[] inputCommand = playerInput.split(CommunicatingProtocol.getSeparator());
        this.type = Command.valueOf(inputCommand[0]);
        loadValue(inputCommand);
    }

    private void loadValue(String[] inputCommand) {
        if (inputCommand.length == 2) {
            this.valueOptional = Optional.of(inputCommand[1]);
        }
        else{
            this.valueOptional = Optional.empty();
        }
    }

    Command getType() {
        return type;
    }

    String getValue() {
        return valueOptional.orElse("");
    }
}
