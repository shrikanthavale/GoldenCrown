package com.shrikane.goldencrown.service;

import com.shrikane.goldencrown.dto.Kingdoms;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class DecipherServiceTest {

    private DecipherService decipherService;

    @BeforeEach
    public void setup() {
        decipherService = new DecipherService();
    }

    @Test
    void testDecipherText() {
        final Map<Kingdoms, String> crypticText = new LinkedHashMap<>();
        crypticText.put(Kingdoms.LAND, "FDIXXSOKKOFBBMU");
        List<Kingdoms> kingdoms = decipherService.decipherMessage(crypticText);
        Assertions.assertIterableEquals(kingdoms, Collections.singletonList(Kingdoms.LAND));
    }
}
