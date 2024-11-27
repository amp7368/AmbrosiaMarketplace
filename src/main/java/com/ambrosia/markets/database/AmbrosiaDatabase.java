package com.ambrosia.markets.database;

import apple.lib.ebean.database.AppleEbeanDatabase;
import apple.lib.ebean.database.config.AppleEbeanDatabaseConfig;
import apple.lib.ebean.database.config.AppleEbeanPostgresConfig;
import com.ambrosia.markets.config.AmbrosiaConfig;
import com.ambrosia.markets.database.model.base.BaseEntity;
import com.ambrosia.markets.database.model.base.BaseEventEntity;
import com.ambrosia.markets.database.model.base.image.DImage;
import com.ambrosia.markets.database.model.entity.client.DClient;
import com.ambrosia.markets.database.model.entity.client.name.ClientDiscordDetails;
import com.ambrosia.markets.database.model.entity.client.name.ClientMinecraftDetails;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameHistory;
import com.ambrosia.markets.database.model.entity.client.name.DClientNameMeta;
import com.ambrosia.markets.database.model.entity.client.rank.DClientRank;
import com.ambrosia.markets.database.model.entity.staff.DStaffConductor;
import com.ambrosia.markets.database.model.interaction.DClientClick;
import com.ambrosia.markets.database.model.item.DItem;
import com.ambrosia.markets.database.model.item.data.DItemData;
import com.ambrosia.markets.database.model.item.pricecheck.DPriceCheck;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.item.stack.DMiscItem;
import com.ambrosia.markets.database.model.message.log.DLog;
import com.ambrosia.markets.database.model.profile.auction.DClientAuction;
import com.ambrosia.markets.database.model.profile.auction.item.DAuctionItem;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOffer;
import com.ambrosia.markets.database.model.profile.auction.offer.DAuctionOfferStatusChange;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import com.ambrosia.markets.database.model.trade.cost.DCostItem;
import com.ambrosia.markets.database.model.trade.cost.DCostItemMisc;
import com.ambrosia.markets.database.model.trade.transfer.DTransferAction;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import java.util.Collection;
import java.util.List;

public class AmbrosiaDatabase extends AppleEbeanDatabase {

    @Override
    protected void addEntities(List<Class<?>> entities) {
        entities.add(BaseEntity.class);
        entities.add(BaseEventEntity.class);
        // client
        entities.addAll(List.of(
            DClientNameHistory.class, DClientNameMeta.class,
            ClientDiscordDetails.class, ClientMinecraftDetails.class
        ));
        entities.add(DClient.class);
        // permissions
        entities.addAll(List.of(DStaffConductor.class, DClientRank.class));

        entities.add(DClientClick.class);

        entities.addAll(List.of(
            DTransferAction.class,
            DCost.class, DCostItemMisc.class, DCostItem.class
        ));
        entities.addAll(List.of(
            DItemData.class, DItem.class, DItemSnapshot.class, DMiscItem.class,
            DPriceCheck.class
        ));

        // auction
        entities.addAll(List.of(
            DClientAuction.class,
            DAuctionItem.class, DAuctionOffer.class,
            DAuctionOfferStatusChange.class
        ));

        // misc
        entities.add(DImage.class);
        entities.add(DLog.class);

    }

    @Override
    protected DatabaseConfig configureDatabase(DataSourceConfig dataSourceConfig) {
        return super.configureDatabase(dataSourceConfig)
            .setRunMigration(true)
            .setDdlRun(true)
            .setDdlGenerate(true)
            .setDdlCreateOnly(!shouldDropDatabase());
    }

    private boolean shouldDropDatabase() {
        return !AmbrosiaConfig.get().isProduction();
    }

    @Override
    protected Collection<Class<?>> getQueryBeans() {
        return List.of();
    }

    @Override
    protected AppleEbeanDatabaseConfig getConfig() {
        return AmbrosiaDatabaseConfig.get();
    }

    @Override
    protected boolean isDefault() {
        return true;
    }

    @Override
    protected String getName() {
        return "Ambrosia";
    }

    public static class AmbrosiaDatabaseConfig extends AppleEbeanPostgresConfig {

        private static AmbrosiaDatabaseConfig instance;


        public AmbrosiaDatabaseConfig() {
            instance = this;
        }

        public static AmbrosiaDatabaseConfig get() {
            return instance;
        }

        @Override
        public boolean shouldRunMigration() {
            return false;
        }
    }
}