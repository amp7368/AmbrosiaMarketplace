package com.ambrosia.markets.api.base.client;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.database.model.entity.client.ClientApi.ClientQueryApi;
import com.ambrosia.markets.database.model.entity.client.DClient;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.HttpResponseException;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public class ClientParams {

    @NotNull
    public static DClient findClientOrThrow(@NotNull String clientNameOrId) throws HttpResponseException {
        if (clientNameOrId.length() > BaseController.STRING_MAX_FIELD_LENGTH)
            throw new BadRequestResponse("Client name too long");

        UUID clientId;
        try {
            clientId = UUID.fromString(clientNameOrId);
        } catch (IllegalArgumentException _) {
            clientId = null;
        }
        if (clientId == null)
            return findClientOrThrow(clientNameOrId, true);
        else return findClientOrThrow(clientId, true);
    }

    private static DClient findClientOrThrow(UUID clientId, boolean nonNull) throws HttpResponseException {
        DClient client = ClientQueryApi.findById(clientId);
        if (nonNull && client == null) throw new BadRequestResponse("Client with id " + clientId + " not found");
        return client;
    }

    private static DClient findClientOrThrow(String clientName, boolean nonNull) throws HttpResponseException {
        DClient client = ClientQueryApi.findByName(clientName);
        if (nonNull && client == null) throw new BadRequestResponse("Client with name " + clientName + " not found");
        return client;
    }
}
