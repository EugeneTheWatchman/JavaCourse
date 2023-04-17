package ru.croc.task11;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class auctionLot {
    private long currentPrice;
    private String userNameOfPriceSetter;
    private final LocalDateTime endTime;
    private static final ReentrantLock locker = new ReentrantLock();

    public auctionLot (LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean makeBid(long newPrice, String userName) {
        if (this.endTime.isBefore(LocalDateTime.now())) {
            return false;
        }

        locker.lock();
        if (newPrice <= this.currentPrice) {
            return false;
        }

        this.currentPrice = newPrice;
        this.userNameOfPriceSetter = userName;
        locker.unlock();

        return true;
    }
}
