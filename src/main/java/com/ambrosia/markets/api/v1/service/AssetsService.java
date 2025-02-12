package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.database.model.base.image.DImage;
import com.ambrosia.markets.database.model.base.image.query.QDImage;
import java.util.UUID;

public class AssetsService {

    public static DImage getImage(UUID imageId) {
        return new QDImage().where()
            .id.eq(imageId)
            .findOne();
    }
}
