package com.battleships.start_window.data_insertion;

import com.battleships.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Responsible for creating columns and rows bars to choose the boars size
 */

public class SettingsSeekBarsData {
    @FXML
    private Label rowsTextLabel;
    @FXML
    private Slider rowsProgressBar;
    @FXML
    private Label rowsAmountDisplayer;


    @FXML
    private Label columnsTextLabel;
    @FXML
    private Slider columnsProgressBar;
    @FXML
    private Label columnsAmountDisplayer;

    /**
     * Initializes bars for choosing columns and rows number of the board
     */
    public void initialize() {
        initRowsProgressBar();
        initColumnsProgressBar();
    }

    private void initRowsProgressBar() {
        Translator.bind(rowsTextLabel.textProperty(), "rows_count");
        rowsProgressBar.valueProperty()
                .addListener((observableValue, oldValue, newValue) ->
                        rowsAmountDisplayer.setText(String.valueOf(newValue.intValue())));
    }

    private void initColumnsProgressBar() {
        Translator.bind(columnsTextLabel.textProperty(), "columns_count");
        columnsProgressBar.valueProperty()
                .addListener((observableValue, oldValue, newValue) ->
                        columnsAmountDisplayer.setText(String.valueOf(newValue.intValue())));
    }
}
