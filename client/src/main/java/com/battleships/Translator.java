package com.battleships;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {
    private static final ObjectProperty<Locale> locale;

    static {
        locale = new SimpleObjectProperty<>(Locale.getDefault());
        locale.addListener(((observable, oldValue, newValue) -> Locale.setDefault(newValue)));
    }

    private Translator() { }

    private static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        Translator.locale.set(locale);
        Locale.setDefault(locale);
    }

    private static String getTranslation(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("translations", getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }

    public static StringBinding createStringBinding(final String key, Object... args) {
        return Bindings.createStringBinding(() -> getTranslation(key, args), locale);
    }

    public static void bind(StringProperty stringProperty, String key, Object... args) {
        stringProperty.bind(createStringBinding(key, args));
    }
}
