package com.example.url_shortener.utilities;

import java.util.UUID;

public class GenerateUID{
    public static String Generate() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString().replace("-", "").substring(0, 12);
        return  uniqueId;
    }
}