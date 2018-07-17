package com.battleships.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageTranslator {
    private MessageTranslator() {
    }

    private static final Pattern pattern = Pattern.compile("\\[(\\S+)\\] \\[(\\S+)\\]");

    public static String prepareMessage(CommandType commandType, Object object) {
        return String.format("[%s] [%s]", commandType.name(), object);
    }

    public static String getMessageContent(String message) {
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches())
            return matcher.group(2);
        else throw new IllegalArgumentException();
    }

    public static CommandType getCommandTypeFromMessage(String message) {
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches())
            return CommandType.valueOf(matcher.group(1));
        else throw new IllegalArgumentException();
    }
}
