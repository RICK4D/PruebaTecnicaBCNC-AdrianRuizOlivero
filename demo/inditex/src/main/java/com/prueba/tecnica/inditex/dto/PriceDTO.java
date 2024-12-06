package com.prueba.tecnica.inditex.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
public class PriceDTO extends BaseDTO {
    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Integer priceList;

    @NotNull
    private Integer priority;

    @NotNull
    private Double price;

    @NotNull
    private String currency;

    @NotNull
    private Long brandId;

    @NotNull
    private Long productId;

    public PriceDTO() {
        super();
    }

    @Override
    public String toString() {
        return "PriceDTO{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", productId=" + productId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", priority=" + priority +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
