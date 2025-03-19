package com.ambrosia.markets.api.v1.controller.transfers;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.dto.transfer.TransferActionPendingDto;
import com.ambrosia.markets.api.v1.controller.transfers.confirm.ConfirmTransferRequest;
import com.ambrosia.markets.api.v1.controller.transfers.confirm.ConfirmTransferRequestInput;
import com.ambrosia.markets.api.v1.service.TransferService;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.trade.transfer.DTransferActionPending;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;

public class TransfersController extends BaseController {

    public void createTransferRequest(Context ctx) throws HttpResponseException {
        DClient requester = BaseClientAuthorizationRequest.clientAuthorization(ctx);

        ConfirmTransferRequestInput input = validateBody(ctx, ConfirmTransferRequestInput.validator,
            ConfirmTransferRequestInput.class);
        ConfirmTransferRequest request = new ConfirmTransferRequest(input, requester);

        DTransferActionPending transfer = TransferService.confirmTransferRequest(request);
        ctx.json(new TransferActionPendingDto(transfer));
    }
}
