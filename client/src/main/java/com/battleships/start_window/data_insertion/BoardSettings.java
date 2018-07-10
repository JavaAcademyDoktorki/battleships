package com.battleships.start_window.data_insertion;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * JavaFx bean for handling of the game parameters
 * rowCount - number of rows
 * columnCount - number of columns
 */
public class BoardSettings {
    private static final int INITIAL_ROW_COLUMN_COUNT = 10;
    private final IntegerProperty rowCount = new SimpleIntegerProperty(INITIAL_ROW_COLUMN_COUNT);
    private final IntegerProperty columnCount = new SimpleIntegerProperty(INITIAL_ROW_COLUMN_COUNT);

    public int getRowCount() {
        return rowCount.get();
    }

    IntegerProperty rowCountProperty() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount.set(rowCount);
    }

    public int getColumnCount() {
        return columnCount.get();
    }

    IntegerProperty columnCountProperty() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount.set(columnCount);
    }
}
