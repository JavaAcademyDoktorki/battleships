//package com.battleships.Commands.CommandsImpl;
//
//import com.battleships.commands.AbstractCommand;
//import com.battleships.commands.CommandType;
//import com.battleships.commands.Message;
//import com.battleships.player.ConnectedPlayers;
//
//public class MessageToPlayer<V> extends AbstractCommand {
//    public MessageToPlayer(V value) {
//        super(value);
//    }
//
//    @Override
//    public void execute(ConnectedPlayers connectedPlayers) {
//        String messageToSend = (String) this.value;
//        System.out.println(messageToSend);
//        Message<String> newMessage = new Message<>(CommandType.MESSAGE, messageToSend);
//        connectedPlayers.sendToInactive(newMessage);
//    }
//}
