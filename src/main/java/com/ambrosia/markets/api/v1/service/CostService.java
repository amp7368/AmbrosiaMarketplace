package com.ambrosia.markets.api.v1.service;

import com.ambrosia.markets.api.request.cost.CostRequest;
import com.ambrosia.markets.database.model.trade.cost.DCost;
import com.ambrosia.markets.database.model.trade.cost.DCostItem;
import com.ambrosia.markets.database.model.trade.cost.DCostItemMisc;
import io.ebean.Transaction;
import java.util.List;

public class CostService {

    public static DCost createCost(CostRequest willingToPay, Transaction transaction) {
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
}
