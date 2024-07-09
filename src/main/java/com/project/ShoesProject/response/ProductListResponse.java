package com.project.ShoesProject.response;

import lombok.*;

import java.util.List;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductListResponse {
    private List<ProductResponse> products;
    private int totalPages;
}
