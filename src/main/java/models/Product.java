package models;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private Integer position;

    private String name;

    private Boolean isHotSale;

    private Integer priceOld;

    private Integer priceNew;

}
