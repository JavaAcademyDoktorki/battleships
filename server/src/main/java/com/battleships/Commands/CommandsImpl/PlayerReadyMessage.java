//package com.battleships.Commands.CommandsImpl;
//
//import com.battleships.commands.AbstractCommand;
//import com.battleships.commands.CommandType;
//import com.battleships.commands.Message;
//import com.battleships.player.ConnectedPlayers;
//
//public class PlayerReadyMessage<V> extends AbstractCommand {
//    public PlayerReadyMessage(V value) {
//        super(value);
//    }
//
//    @Override
//    public void execute(ConnectedPlayers connectedPlayers) {
//        Message<Boolean> messageToOtherClient = new Message<>(CommandType.PLAYER_READY, Boolean.TRUE);
//        connectedPlayers.sendToActive(messageToOtherClient);
//    }
//}
