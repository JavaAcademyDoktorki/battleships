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
    private IntegerProperty rowCount = new SimpleIntegerProperty(INITIAL_ROW_COLUMN_COUNT);
    private IntegerProperty columnCount = new SimpleIntegerProperty(INITIAL_ROW_COLUMN_COUNT);

    public int getRowCount() {
        return rowCount.get();
    }

    public IntegerProperty rowCountProperty() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount.set(rowCount);
    }

    public int getColumnCount() {
        return columnCount.get();
    }

    public IntegerProperty columnCountProperty() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount.set(columnCount);
    }
}
