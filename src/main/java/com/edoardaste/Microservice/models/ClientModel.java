package com.edoardaste.Microservice.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="CLIENT_PRODUCT")
public class ClientModel implements Serializable {
    private static final long serialVersionUI = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    private String nameClient;
    private String addressClient;

    public UUID getIdProduct() {
        return idClient;
    }

    public void setIdProduct(UUID idProduct) {
        this.idClient = idProduct;
    }

    public String getNameProduct() {
        return nameClient;
    }

    public void setNameProduct(String nameProduct) {
        this.nameClient = nameProduct;
    }

    public String getAddressClient() {
        return addressClient;
    }

    public void setAddressClient(String addressClient) {
        this.addressClient = addressClient;
    }
}
