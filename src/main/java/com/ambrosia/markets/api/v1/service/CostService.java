package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.request.cost.CostRequest;
import com.ambrosia.markets.api.request.miscitem.MiscItemRequest;
import com.ambrosia.markets.database.model.item.snapshot.DItemSnapshot;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import com.ambrosia.markets.database.model.trade.cost.DCostItem;
import com.ambrosia.markets.database.model.trade.cost.DCostItemMisc;
import com.ambrosia.markets.database.system.exception.AlreadySoldException;
import io.ebean.Transaction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CostService {

    public static DCost createCost(CostRequest willingToPay, Transaction transaction) throws AlreadySoldException {
        for (DItemSnapshot item : willingToPay.getItems())
            item.verifyCurrentlyOwned();

        DCost cost = new DCost(willingToPay.getEmeralds(), willingToPay.isExtended());
        List<DCostItem> items = willingToPay.getItems().stream()
            .map(item -> new DCostItem(cost, item))
            .toList();
        List<DCostItemMisc> miscItems = willingToPay.getMiscItems().stream()
            .map(req -> new DCostItemMisc(cost, req.getItem(), req.getQuantity()))
            .toList();
        items.forEach(cost::addItem);
        miscItems.forEach(cost::addMiscItem);

        cost.save(transaction);
        items.forEach(e -> e.save(transaction));
        miscItems.forEach(e -> e.save(transaction));

        return cost;
    }

    public static boolean isEqual(CostRequest costA, DCost costB) {
        if (!costA.getEmeralds().eq(costB.getEmeralds())) {
            return false;
        }
        Set<UUID> itemsA = costA.getItems().stream().map(DItemSnapshot::getId).collect(Collectors.toSet());
        Set<UUID> itemsB = costB.getItems().stream()
            .map(DItemSnapshot::getId)
            .collect(Collectors.toSet());
        if (!itemsA.equals(itemsB)) return false;

        Set<MiscItemRequest> miscA = new HashSet<>(costA.getMiscItems());
        Set<MiscItemRequest> miscB = costB.getMiscItems().stream()
            .map(req -> new MiscItemRequest(req.getItem(), req.getQuantity()))
            .collect(Collectors.toSet());
        return miscA.equals(miscB);
    }
}
