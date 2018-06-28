package com.battleships;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Labeled;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.Callable;

public class Translator {
    private static final ObjectProperty<Locale> locale;

    static {
        locale = new SimpleObjectProperty<>(Locale.getDefault());
        locale.addListener(((observable, oldValue, newValue) -> Locale.setDefault(newValue)));
    }

    static List<Locale> getSupportedLocales() {
        return new ArrayList<>(Arrays.asList(
                new Locale("pl"),
                new Locale("en")));
    }

    public static Locale getDefaultLocale() {
        Locale sysDefault = Locale.getDefault();
        return getSupportedLocales().contains(sysDefault) ? sysDefault : new Locale("pl");
    }

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        localeProperty().set(locale);
        Locale.setDefault(locale);
    }

    public static ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public static String get(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("translations", getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }

    public static StringBinding createStringBinding(final String key, Object... args) {
        return Bindings.createStringBinding(() -> get(key, args), locale);
    }

    public static StringBinding createStringBinding(Callable<String> func) {
        return Bindings.createStringBinding(func, locale);
    }

    public static void bind(Labeled settingsLabel, String key, Object... args) {
        settingsLabel.textProperty().bind(createStringBinding(key, args));
    }
}
