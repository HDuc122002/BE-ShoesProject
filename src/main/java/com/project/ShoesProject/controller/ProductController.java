package com.project.ShoesProject.controller;

<<<<<<< HEAD
import com.github.javafaker.Faker;
=======
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13
import com.project.ShoesProject.dto.ProductDTO;
import com.project.ShoesProject.entity.Product;
import com.project.ShoesProject.exception.DataNotFoundException;
import com.project.ShoesProject.response.ProductListResponse;
import com.project.ShoesProject.response.ProductResponse;
import com.project.ShoesProject.service.Impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<ProductListResponse> getAllProduct(@RequestParam("page") int page
                                           , @RequestParam("limit") int limit){
        PageRequest pageRequest = PageRequest.of(page,limit, Sort.by(new String[]{"createdAt"}).descending());
        Page<ProductResponse> productPage = productService.getAll(pageRequest);
        int totalPages = productPage.getTotalPages();
        List<ProductResponse> products = productPage.getContent();
        return ResponseEntity.ok(ProductListResponse.builder()
                        .products(products)
                        .totalPages(totalPages)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        try {
            Product existingProduct = productService.getById(id);
            return ResponseEntity.ok(ProductResponse.fromProduct(existingProduct));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) throws DataNotFoundException {
        try {
            productService.create(productDTO);
            return ResponseEntity.ok("create product successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ProductDTO productDTO,
                                    @PathVariable Long id){
        try {
            Product updatedProduct = productService.update(id, productDTO);
            return ResponseEntity.ok("Product updated successfully: "+updatedProduct);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) Long categoryId) {
        try {
            List<Product> products = productService.search(name,categoryId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @PostMapping("/generateData")
//    public ResponseEntity<String> generateData(){
//        Faker faker = new Faker();
//        for (int i=0;i<1000;i++){
//            String productName = faker.commerce().productName();
//            if (productService.existsByName(productName)) {
//                continue;
//            }
//            ProductDTO productDTO = ProductDTO.builder()
//                    .name(productName)
//                    .price((float)faker.number().numberBetween(0,1000))
//                    .description(faker.lorem().sentence())
//                    .thumbnail("")
//                    .categoryId((long) faker.number().numberBetween(1,5))
//                    .build();
//            try {
//                productService.create(productDTO);
//            } catch (DataNotFoundException e) {
//                return ResponseEntity.badRequest().body(e.getMessage());
//            }
//        }
//        return ResponseEntity.ok("Faker product created successfully");
//    }
}
