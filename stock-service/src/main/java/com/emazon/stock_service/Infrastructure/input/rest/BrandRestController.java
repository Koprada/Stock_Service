package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.brandDto.BrandDtoRequest;
import com.emazon.stock_service.Application.dto.brandDto.BrandDtoResponse;
import com.emazon.stock_service.Application.handler.brandHandler.BrandHandler;
import com.emazon.stock_service.Domain.model.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class BrandRestController {

    private final BrandHandler brandHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveBrand(@RequestBody BrandDtoRequest brandDtoRequest) {
        brandHandler.saveBrand(brandDtoRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<Pagination<BrandDtoResponse>> listBrands(
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pagination<BrandDtoResponse> pagination = brandHandler.listBrands(sortOrder, page, size);
        return ResponseEntity.ok(pagination);
    }

}
