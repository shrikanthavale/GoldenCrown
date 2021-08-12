package com.shrikane.goldencrown.service;

import com.shrikane.goldencrown.dto.Kingdoms;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class is used for reading the file from the given command line argument. After reading the file contents
 * it maps the content to kingdom and ciphered text. This ciphered text is later given to @{@link DecipherService} for
 * decrypting and checking if the hidden message is present.
 */
public class ReadFileService {

    /**
     * Reads the file from given file location or else throws an exception.
     *
     * @param filePath path from where file is to be read.
     * @return returns map of kingdoms and read cryptic text.
     * @throws IOException in case file cannot be read.
     */
    public Map<Kingdoms, String> readFileAndMapCiphers(String filePath) throws IOException {

        if (StringUtils.isEmpty(filePath)) {
            return Collections.emptyMap();
        }

        final Map<Kingdoms, String> kingdomWithCipher = new LinkedHashMap<>(Kingdoms.values().length);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String[] kingdomCipherSplit = line.split(" ", 2);
                final Kingdoms kingdom = convertFrom(kingdomCipherSplit[0]);
                if (kingdom == null) {
                    continue;
                }

                kingdomWithCipher.computeIfAbsent(kingdom, tempKingdom -> kingdomCipherSplit[1]);
            }
        }

        return kingdomWithCipher;
    }

    private static Kingdoms convertFrom(final String kingdomNameString) {
        try {
            return Kingdoms.valueOf(kingdomNameString);
        } catch (final IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }
}
