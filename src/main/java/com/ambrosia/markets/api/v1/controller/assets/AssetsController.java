package com.ambrosia.markets.api.v1.controller.assets;

import com.ambrosia.markets.api.base.BaseController;
import com.ambrosia.markets.api.v1.service.AssetsService;
import com.ambrosia.markets.database.model.base.image.DImage;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

public class AssetsController extends BaseController {

    public void getImage(Context ctx) throws InternalServerErrorResponse {
        String imageInput = ctx.pathParam("image");
        UUID imageId;
        try {
            imageId = UUID.fromString(imageInput);
        } catch (IllegalArgumentException e) {
            throw new BadRequestResponse("'%s' is not a valid image-id".formatted(imageInput));
        }
        DImage image = AssetsService.getImage(imageId);

        try {
            FileInputStream inputStream = new FileInputStream(image.getImage());
            ctx.result(inputStream);
            ctx.contentType(image.getContentType());
        } catch (IOException e) {
            throw new InternalServerErrorResponse();
        }
    }
}
