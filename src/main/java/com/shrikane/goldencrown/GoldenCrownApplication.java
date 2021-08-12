package com.shrikane.goldencrown;

import com.shrikane.goldencrown.dto.Kingdoms;
import com.shrikane.goldencrown.service.DecipherService;
import com.shrikane.goldencrown.service.ReadFileService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoldenCrownApplication {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("NONE");
            return;
        }

        if (args.length > 1) {
            System.out.println("NONE");
            return;
        }

        final ReadFileService readFileService = new ReadFileService();
        final DecipherService decipherService = new DecipherService();

        try {
            final Map<Kingdoms, String> kingdomCipherMap = readFileService.readFileAndMapCiphers(args[0]);
            if (kingdomCipherMap == null || kingdomCipherMap.isEmpty()) {
                System.out.println("NONE");
                return;
            }

            List<Kingdoms> kingdoms = decipherService.decipherMessage(kingdomCipherMap);
            if (kingdoms == null || kingdoms.isEmpty()) {
                System.out.println("NONE");
                return;
            }

            if (kingdoms.size() < 3) {
                System.out.println("NONE");
                return;
            }

            System.out.println(Kingdoms.SPACE + " " + kingdoms.stream().map(kingdom -> kingdom.name()).collect(Collectors.joining(" ")));

        } catch (final Exception exception) {
            System.out.println("NONE");
        }
    }
}
