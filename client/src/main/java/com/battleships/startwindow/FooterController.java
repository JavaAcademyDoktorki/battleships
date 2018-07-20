package com.battleships.startwindow;

import com.battleships.Translator;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionModel;

import java.util.Locale;

/**
 * Responsible for a common footer of each screen that enables to change language
 */
public class FooterController {
    @FXML
    private ChoiceBox<String> languageSelector;

    /**
     * Initializes language settings to be chosen by a player
     */
    @FXML
    public void initialize() {
        setupLanguageSelector();
    }

    private void setupLanguageSelector() {
        setItemsForLanguageSelector();
        setListenerForLanguageSelector(getLanguageChangeListenerAction());
        selectFirstItemInLanguageSelector();
    }

    private void setItemsForLanguageSelector() {
        languageSelector.setItems(FXCollections.observableArrayList("pl", "en"));
    }

    private void setListenerForLanguageSelector(ChangeListener<String> changeListener) {
        SelectionModel<String> changeLanguageSelectionModel = languageSelector.getSelectionModel();
        ReadOnlyObjectProperty<String> readOnlyObjectProperty = changeLanguageSelectionModel.selectedItemProperty();
        readOnlyObjectProperty.addListener(changeListener);
    }

    private ChangeListener<String> getLanguageChangeListenerAction() {
        return (observable, oldV, newV) -> Translator.setLocale(new Locale(newV));
    }

    private void selectFirstItemInLanguageSelector() {
        SelectionModel<String> changeLanguageSelectionModel = languageSelector.getSelectionModel();
        changeLanguageSelectionModel.selectFirst();
    }
}
