package tbarlow.controller;

public class Utilities {
    public void sleep(long time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
