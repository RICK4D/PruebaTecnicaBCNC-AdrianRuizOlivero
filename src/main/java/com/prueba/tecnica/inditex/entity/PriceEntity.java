package com.prueba.tecnica.inditex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * The type Price entity.
 */
@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "prices")
public class PriceEntity extends BaseEntity {

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price_list", nullable = false)
    private Integer priceList;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "currency", nullable = false, length = 5)
    private String currency;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand; // Relación con la entidad Brand

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product; // Relación con la entidad Product

    /**
     * Instantiates a new Price entity.
     */
    public PriceEntity() {
        super();
    }

    /**
     * Instantiates a new Price entity.
     *
     * @param brand     the brand
     * @param product   the product
     * @param startDate the start date
     * @param endDate   the end date
     * @param priceList the price list
     * @param priority  the priority
     * @param price     the price
     * @param currency  the currency
     */
    public PriceEntity(BrandEntity brand, ProductEntity product, LocalDateTime startDate, LocalDateTime endDate,
                 Integer priceList, Integer priority, Double price, String currency) {
        super();
        this.brand = brand;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + getId() +
                ", brand=" + (brand != null ? brand.getId() : null) +
                ", product=" + (product != null ? product.getId() : null) +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", priority=" + priority +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}