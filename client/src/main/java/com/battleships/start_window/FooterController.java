package com.battleships.start_window;

import com.battleships.Translator;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionModel;

import java.util.Locale;

public class FooterController {
    @FXML
    private ChoiceBox<String> languageSelector;

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
        SelectionModel<String> changeLanguageSelecetionModel = languageSelector.getSelectionModel();
        ReadOnlyObjectProperty<String> readOnlyObjectProperty = changeLanguageSelecetionModel.selectedItemProperty();
        readOnlyObjectProperty.addListener(changeListener);
    }

    private ChangeListener<String> getLanguageChangeListenerAction() {
        return (observable, oldV, newV) -> Translator.setLocale(new Locale(newV));
    }

    private void selectFirstItemInLanguageSelector() {
        SelectionModel<String> changeLanguageSelecetionModel = languageSelector.getSelectionModel();
        changeLanguageSelecetionModel.selectFirst();
    }
}
