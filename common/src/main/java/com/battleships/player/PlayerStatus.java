package com.battleships.player;

public enum PlayerStatus {
    ACTIVE() {
        @Override
        public PlayerStatus other() {
            return INACTIVE;
        }
    }, INACTIVE() {
        @Override
        public PlayerStatus other() {
            return ACTIVE;
        }
    };

    public abstract PlayerStatus other();
}
