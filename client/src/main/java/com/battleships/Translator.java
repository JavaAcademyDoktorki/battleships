package com.battleships;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Responsible for a translation according to chosen language settings
 */
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

    /**
     * Sets a new locale based on a new value provided by a player
     *
     * @param locale -a new locale to be set up
     */
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

    /**
     * Translate text on @FXML object according to chosen language settings
     *
     * @param stringProperty - text on @FXML object
     * @param key - text command of the properties file
     * @param args - arguments of the properties file
     */
    public static void bind(StringProperty stringProperty, String key, Object... args) {
        stringProperty.bind(createStringBinding(key, args));
    }
}
