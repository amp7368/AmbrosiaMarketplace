package com.ambrosia.markets.database.model.entity.client;

import com.ambrosia.markets.database.model.entity.client.ClientApi.ClientQueryApi;
import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import me.xdrop.fuzzywuzzy.FuzzySearch;

public class ClientSearch {

    public static List<String> autoComplete(String match) {
        List<ClientName> byName = new ArrayList<>();

        List<DClientNameMeta> clientNames = ClientQueryApi.findAllNameMetaReadOnly();
        for (DClientNameMeta clientName : clientNames) {
            String displayName = clientName.getDisplayName();
            String minecraft = clientName.getMinecraft().getUsername();
            ClientDiscordDetails discordDetails = clientName.getDiscord(false);
            String discord = discordDetails.getUsername();

            byName.add(new ClientName(clientName, displayName, discord, minecraft));
        }

        byName.forEach(c -> c.match(match));

        byName.sort(Comparator.comparing(ClientName::score).reversed());
        return byName.stream().map(ClientName::getEffectiveName).toList();
    }

    private static class ClientName {

        private final List<String> names;
        private final DClientNameMeta clientName;
        private int score;

        private ClientName(DClientNameMeta clientName, String... names) {
            this.clientName = clientName;
            this.names = Arrays.stream(names).filter(Objects::nonNull).toList();
        }

        protected void match(String match) {
            String matchLower = match.toLowerCase();
            for (String name : names) {
                int score = FuzzySearch.partialRatio(matchLower, name.toLowerCase());
                if (score > this.score)
                    this.score = score;
            }
        }

        protected int score() {
            return this.score;
        }

        public String getEffectiveName() {
            return this.clientName.getEffectiveName();
        }
    }
}
