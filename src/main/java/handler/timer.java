package handler;

import java.util.Timer;
import java.util.TimerTask;

import static function.datacheck.DataCheck;
import static function.shop.Shop;

public class timer extends TimerTask {
    public static Timer timer = new Timer();

    public void run() {
        DataCheck();
        Shop();
    }

    public static void main() {
        timer.schedule(new timer(), 1000, 5000);
    }
}

