SELECT *
FROM auction_offer;
SELECT *
FROM transfer_action
         LEFT JOIN cost c1 ON transfer_action.seller_cost_id = c1.id
;



ALTER TABLE auction_offer
    ADD COLUMN seller uuid;