package com.epiis.apirfbvc.business;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.epiis.apirfbvc.dto.response.ResponseLotGetAll;
import com.epiis.apirfbvc.entity.EntityLot;
import com.epiis.apirfbvc.repository.RepositoryLot;

@Service
public class BusinessLot {

    private final RepositoryLot repositoryLot;

    public BusinessLot(RepositoryLot repositoryLot) {
        this.repositoryLot = repositoryLot;
    }

    public ResponseLotGetAll getAll() {
        ResponseLotGetAll response = new ResponseLotGetAll();

        List<EntityLot> list = repositoryLot.findAll();

        List<Map<String, String>> items = list.stream()
            .map(this::toMap)
            .collect(Collectors.toList());

        response.setListLots(items);
        response.success();
        return response;
    }

    private Map<String, String> toMap(EntityLot lot) {
        Map<String, String> data = new HashMap<>();

        data.put("idLot", lot.getIdLot());
        data.put("code", lot.getCode());
        data.put("expirationDate", lot.getExpirationDate() != null
            ? lot.getExpirationDate().toString() : "");
        data.put("purchasePrice", lot.getPurchasePrice() != null
            ? lot.getPurchasePrice().toString() : "0");
        data.put("currentStock", String.valueOf(lot.getCurrentStock()));
        data.put("createdAt", lot.getCreatedAt() != null
            ? lot.getCreatedAt().toString() : "");

        data.put("idProduct", lot.getProduct() != null ? lot.getProduct().getIdProduct() : "");
        data.put("productName", lot.getProduct() != null ? lot.getProduct().getName() : "—");

        data.put("idSupplier", lot.getSupplier() != null ? lot.getSupplier().getIdSupplier() : "");
        data.put("supplierName", lot.getSupplier() != null ? lot.getSupplier().getName() : "—");

        data.put("expirationStatus", calcularEstadoVencimiento(lot.getExpirationDate()));

        return data;
    }

    private String calcularEstadoVencimiento(LocalDate expirationDate) {
        if (expirationDate == null) return "Sin fecha";

        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(30);

        if (expirationDate.isBefore(hoy)) {
            return "Vencido";
        } else if (!expirationDate.isAfter(limite)) {
            return "Por vencer";
        } else {
            return "Vigente";
        }
    }
}