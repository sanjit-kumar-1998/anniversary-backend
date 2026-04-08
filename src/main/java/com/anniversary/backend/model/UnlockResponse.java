package com.anniversary.backend.model;

public class UnlockResponse {
    private boolean unlocked;
    private String message;
    private boolean canAttempt;
    private long millisecondsUntilUnlock;

    public UnlockResponse(boolean unlocked, String message, boolean canAttempt, long millisecondsUntilUnlock) {
        this.unlocked = unlocked;
        this.message = message;
        this.canAttempt = canAttempt;
        this.millisecondsUntilUnlock = millisecondsUntilUnlock;
    }

    // Getters and setters
    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCanAttempt() {
        return canAttempt;
    }

    public void setCanAttempt(boolean canAttempt) {
        this.canAttempt = canAttempt;
    }

    public long getMillisecondsUntilUnlock() {
        return millisecondsUntilUnlock;
    }

    public void setMillisecondsUntilUnlock(long millisecondsUntilUnlock) {
        this.millisecondsUntilUnlock = millisecondsUntilUnlock;
    }
}