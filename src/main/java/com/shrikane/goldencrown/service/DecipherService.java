package com.shrikane.goldencrown.service;

import com.shrikane.goldencrown.dto.Kingdoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DecipherService {

    private static final String ENGLISH_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    public List<Kingdoms> decipherMessage(final Map<Kingdoms, String> crypticMessage) {
        final List<Kingdoms> wonKingdoms = new ArrayList<>(Kingdoms.values().length);
        for (final Map.Entry<Kingdoms, String> entry : crypticMessage.entrySet()) {
            String animal = entry.getKey().getAnimal();
            final String decipheredText = rotateCharacters(animal.length(), entry.getValue());
            if (decipherTextContainsEmblem(animal.toLowerCase(), decipheredText)) {
                wonKingdoms.add(entry.getKey());
            }
        }
        return wonKingdoms;
    }

    private static String rotateCharacters(final int rotateBy, final String cipheredText) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (char tempChar : cipheredText.toLowerCase().toCharArray()) {
            stringBuilder.append(ENGLISH_CHARACTERS.charAt(getRotatedPosition(rotateBy, ENGLISH_CHARACTERS.indexOf(tempChar))));
        }
        return stringBuilder.toString();
    }

    private static boolean decipherTextContainsEmblem(final String emblem, final String decipheredText) {
        final List<Character> emblemCharacters = emblem.chars().mapToObj(char.class::cast).collect(Collectors.toList());
        for (final Character tempChar : decipheredText.toLowerCase().toCharArray()) {
            emblemCharacters.remove(tempChar);
            if (emblemCharacters.isEmpty()) {
                break;
            }
        }

        return emblemCharacters.isEmpty();
    }

    private static int getRotatedPosition(final int rotateBy, final int currentPosition) {
        final int newPosition = currentPosition - rotateBy;
        if (newPosition >= 0) {
            return newPosition;
        } else {
            return newPosition + ENGLISH_CHARACTERS.length();
        }
    }
}
