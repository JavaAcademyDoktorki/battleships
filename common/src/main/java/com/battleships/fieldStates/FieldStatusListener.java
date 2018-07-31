package com.battleships.fieldStates;

import java.io.Serializable;

public interface FieldStatusListener extends Serializable {
    void notifyFieldStateChange();

    void unbindAndDisable();
}
