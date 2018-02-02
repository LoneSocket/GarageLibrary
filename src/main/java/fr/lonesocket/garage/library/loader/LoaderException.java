package fr.lonesocket.garage.library.loader;

public class LoaderException extends Exception {
    LoaderException(String message, Exception e) {
        super(message, e);
    }

    LoaderException(String message) {
        super(message);
    }
}
