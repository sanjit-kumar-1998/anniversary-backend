package com.anniversary.backend.controller;

import com.anniversary.backend.model.UnlockRequest;
import com.anniversary.backend.model.UnlockResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
        "http://10.227.233.57:3000",
        "http://localhost:3000"
})
public class AuthController {

    @Value("${app.unlock.date}")
    private String unlockDateStr;

    @Value("${app.unlock.timezone}")
    private String timezone;

    @Value("${app.unlock.password}")
    private String correctPassword;

    @GetMapping("/status")
    public ResponseEntity<UnlockResponse> getStatus() {
        LocalDateTime unlockDate = LocalDateTime.parse(unlockDateStr);
        ZonedDateTime unlockDateTime = unlockDate.atZone(ZoneId.of(timezone));
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timezone));

        long millisecondsUntilUnlock = ChronoUnit.MILLIS.between(now, unlockDateTime);
        boolean canAttempt = now.isAfter(unlockDateTime) || now.isEqual(unlockDateTime);

        return ResponseEntity.ok(new UnlockResponse(
                false,
                "Enter the password to unlock 💕",
                canAttempt,
                millisecondsUntilUnlock
        ));
    }

    @PostMapping("/unlock")
    public ResponseEntity<UnlockResponse> unlock(@RequestBody UnlockRequest request) {
        LocalDateTime unlockDate = LocalDateTime.parse(unlockDateStr);
        ZonedDateTime unlockDateTime = unlockDate.atZone(ZoneId.of(timezone));
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timezone));

        long millisecondsUntilUnlock = ChronoUnit.MILLIS.between(now, unlockDateTime);
        boolean dateReached = now.isAfter(unlockDateTime) || now.isEqual(unlockDateTime);

        // Check if password is correct
        if (correctPassword.equals(request.getPassword())) {
            // Password is CORRECT! Always return success with date status
            return ResponseEntity.ok(new UnlockResponse(
                    true,  // unlocked (password correct)
                    "Welcome! 💕✨",
                    dateReached, // canAttempt (tells if date reached)
                    millisecondsUntilUnlock
            ));
        } else {
            // Password is WRONG
            return ResponseEntity.ok(new UnlockResponse(
                    false, // unlocked
                    "Wrong password! Try again 💔",
                    dateReached,
                    millisecondsUntilUnlock
            ));
        }
    }
}