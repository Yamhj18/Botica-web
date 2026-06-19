package com.epiis.apirfbvc.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.response.ResponseInventoryIncome;
import com.epiis.apirfbvc.entity.EntityInventoryMovement;
import com.epiis.apirfbvc.entity.EntityInventoryMovementDetail;
import com.epiis.apirfbvc.repository.RepositoryInventory;
import com.epiis.apirfbvc.repository.RepositoryInventoryMovement;
import com.epiis.apirfbvc.repository.RepositoryInventoryMovementDetail;
@Service
public class BusinessInventory {
    private final RepositoryInventoryMovement repositoryMovement;
    private final RepositoryInventoryMovementDetail repositoryMovementDetail;

    public BusinessInventory(RepositoryInventory repositoryInventory,
                             RepositoryInventoryMovement repositoryMovement,
                             RepositoryInventoryMovementDetail repositoryMovementDetail) {
        this.repositoryMovement = repositoryMovement;
        this.repositoryMovementDetail = repositoryMovementDetail;
    }

    public ResponseInventoryIncome getIncomes() {
        ResponseInventoryIncome response = new ResponseInventoryIncome();

        List<EntityInventoryMovement> movimientos = repositoryMovement.findByType("Entrada");

        List<Map<String, Object>> items = movimientos.stream()
            .map(this::toIncomeMap)
            .collect(Collectors.toList());

        response.setListMovements(items);
        response.success();
        return response;
    }

    private Map<String, Object> toIncomeMap(EntityInventoryMovement m) {
        List<EntityInventoryMovementDetail> detalles =
            repositoryMovementDetail.findByMovement_IdMovement(m.getIdMovement());

        List<Map<String, String>> detallesMap = detalles.stream().map(d -> {
            Map<String, String> det = new HashMap<>();
            det.put("idDetail", d.getIdDetail());
            det.put("productName", d.getProduct() != null ? d.getProduct().getName() : "—");
            det.put("lotCode", d.getLot() != null ? d.getLot().getCode() : "—");
            det.put("quantity", String.valueOf(d.getQuantity()));
            det.put("unitCost", d.getUnitCost() != null ? d.getUnitCost().toString() : "0");
            return det;
        }).collect(Collectors.toList());

        Map<String, Object> data = new HashMap<>();
        data.put("idMovement", m.getIdMovement());
        data.put("movementDate", m.getMovementDate().toString());
        data.put("observation", m.getObservation() != null ? m.getObservation() : "");
        data.put("userName", m.getUser() != null
            ? m.getUser().getFirstName() + " " + m.getUser().getSurName()
            : "—");
        data.put("detalles", detallesMap);

        return data;
    }
}