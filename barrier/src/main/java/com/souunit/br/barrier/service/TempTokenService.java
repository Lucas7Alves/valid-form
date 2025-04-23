package com.souunit.br.barrier.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TempTokenService {

    private final Map<String, TempTokenInfo> tokenStore = new HashMap<>();

    public String generateToken(String email) {
        String emailLower = email.toLowerCase();
        String token = UUID.randomUUID().toString().substring(0, 6);
        tokenStore.put(emailLower, new TempTokenInfo(token, System.currentTimeMillis() + 5 * 60 * 1000));
        return token;
    }

    public boolean validateToken(String email, String token) {
        String emailLower = email.toLowerCase();
        TempTokenInfo info = tokenStore.get(emailLower);
        if (info == null) return false;

        boolean isValid = info.getToken().equals(token) && System.currentTimeMillis() < info.getExpiresAt();

        if (isValid) tokenStore.remove(emailLower);
        return isValid;
    }

    private static class TempTokenInfo {
        private final String token;
        private final long expiresAt;

        public TempTokenInfo(String token, long expiresAt) {
            this.token = token;
            this.expiresAt = expiresAt;
        }

        public String getToken() {
            return token;
        }

        public long getExpiresAt() {
            return expiresAt;
        }
    }
}
