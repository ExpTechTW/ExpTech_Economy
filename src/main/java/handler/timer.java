package handler;

import java.util.Timer;
import java.util.TimerTask;

import static function.datacheck.DataCheck;
import static function.shop.Shop;

public class timer extends TimerTask {

    public void run() {
        DataCheck();
        Shop();
    }

    public static void main() {
        Timer timer = new Timer();
        timer.schedule(new timer(), 1000, 5000);
    }
}

