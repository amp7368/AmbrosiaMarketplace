package com.ambrosia.markets.api.v1.controller.transfers;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.base.client.BaseClientAuthorizationRequest;
import com.ambrosia.markets.api.dto.transfer.TransferActionDto;
import com.ambrosia.markets.api.v1.controller.transfers.confirm.ConfirmTransferRequest;
import com.ambrosia.markets.api.v1.controller.transfers.confirm.ConfirmTransferRequestInput;
import com.ambrosia.markets.api.v1.service.TransfersService;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.trade.transfer.DTransferAction;
import io.javalin.http.Context;

public class TransfersController extends BaseController {

    public void createTransfer(Context ctx) {
        DClient requester = BaseClientAuthorizationRequest.clientAuthorization(ctx);

        ConfirmTransferRequestInput input = validateBody(ctx, ConfirmTransferRequestInput.validator,
            ConfirmTransferRequestInput.class);
        ConfirmTransferRequest request = new ConfirmTransferRequest(input, requester);

        DTransferAction transfer = TransfersService.createTransfer(request);
        ctx.json(new TransferActionDto(transfer));
    }
}
