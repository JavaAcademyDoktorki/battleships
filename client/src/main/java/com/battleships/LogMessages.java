package com.battleships;

/**
 * Stores client commands with messages for logging purpose
 */
public class LogMessages {
    public static final String DISCONNECTED_AFTER_PLAYER_REQ_SUCCEED = "Rozłączony z serwerem na żądanie gracza";
    public static final String DISCONNECTED_AFTER_PLAYER_REQ_FAILED = "Rozłączenie z serwerem na żądanie gracza nie powiodło się.";
    public static final String PROBLEM_WHEN_TRYING_TO_DISCONNECT = "Problem z rozłączeniem serwera: ";
    public static final String CANNOT_CONNECT_TO_SERVER = "Nie można podłączyć do serwera: %s:%d";
    public static final String NOT_ABLE_TO_LOAD_MAIN_FXML_VIEW = "Nie mogłem załadować głownego widoku fxml, nie odpaliłem aplikacji.";
    public static final String MAIN_FXML_VIEW_LOADED_APP_STARTED = "Załadowałem główny widok fxml i wystartowałem aplikację.";
    public static final String NOT_ABLE_TO_LOAD_MAIN_FXML_VIEW_FROM_PATH = "Nie mogłem załadować głównego pliku xml aplikacji ze ścieżki %s";
    public static final String COMMAND_SEND_FAILED = "Nie mogę wysłać do serwera komendy \"%s\". Połączenie nie jest nawiązane.";
    public static final String COMMAND_SEND_SUCCEEDED = "Komenda do serwera została wysłana: \"%s\"";
    public static final String WRONG_IP_ADDRESS = "Nie mogłem utworzyć połączenia, nie dostałem poprawnego adresu IP.";
    public static final String WRONG_PORT_NUMBER = "Nie mogłem utworzyć połączenia, nie dostałem poprawnego numeru portu.";
    public static final String SERVERIO_OBJECT_NOT_CREATED = "Nie stworzono obiektu ServerIO. (brak strumienia wyjśćia lub wejścia)";
    public static final String CANNOT_OBTAIN_SOCKET_OUTPUSTREAM = "Nie mogę uzyskać strumienia wyjścia (OutputStream) klienta";
    public static final String CANNOT_OBTAIN_SOCKET_INPUTSTREAM = "Nie mogę uzyskać strumienia wejścia (InputStream) klienta";
    public static final String QUIT_WITH_ESCAPE = "Wyjście przy użyciu Escape";
    public static final String SHIP_PLACED = "Flota została rozlosowana";
    public static final String FIRED_SHOT_ON ="Strzał oddany (wiersz: %d, kolumna: %d)";
    public static final String SHIP_VALIDATED = "Ustawiennie floty zwalidowane. Poprawność: %s";
    public static String INVALID_BOARD_DIMENSIONS = "Wymiary planszy do inicjalizacji są nieprawidłowe: [%d, %d]. " +
            "\nMinimalny wymiar to: %d. " +
            "\nMaksymalny wymiar to: %d.";
}
