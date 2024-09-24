package com.alibou.security.controller;

import com.alibou.security.model.dto.CartDtos;
import com.alibou.security.model.dto.Ob.ObDtos;
import com.alibou.security.model.dto.Ob.OblistDtos;
import com.alibou.security.model.dto.global.DetailDtos;
import com.alibou.security.model.dto.global.PaginationDto;
import com.alibou.security.model.entities.Ob;
import com.alibou.security.service.CartSFService;
import com.alibou.security.service.ObService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/global")
@RequiredArgsConstructor
public class GlobalController {

    private final ObService service;

    private final CartSFService service_SF;

    @GetMapping(value = "/detail-ob")
    public ResponseEntity<?> detail(
            @RequestParam (required = false)Integer id

    ) {
        var ob = service.findbyID(id);
        return ResponseEntity.ok(ob);
    }

    @GetMapping(value = "/related-ob")
    public ResponseEntity<?> related_ob(
            @RequestParam (required = false)Integer id

    ) {

        var ob = service.findbyID(id);
        var ob_1 = new Ob(ob.get().getId(),
                ob.get().getName(),
                ob.get().getPrice(),
                ob.get().getBrand(),
                ob.get().getPictureURL(),
                ob.get().getDescription(),
                ob.get().getCreateDate(),
                ob.get().getCreatedByUserid());
        var brand_index = ob.get().getBrand();
        var list_of = service.find_brand(brand_index);
        list_of.remove(ob_1);
        List<Ob> list_of1 = new ArrayList<>();
        int cnt = 0;
        for (var x :list_of) {
            list_of1.add(x);
            cnt++;
            if(cnt == 6){
                break;
            }
        }
        return ResponseEntity.ok(list_of1);
    }

    @GetMapping(value = "/list-ob", params = {"!brand", "!search", "!priceGte", "!priceLte"})
    public ResponseEntity<OblistDtos> get_detail(
            @RequestParam (required = false)Integer _limit

    ) {
        var list = service.findAll();
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }

    @GetMapping(value = "/list-ob",produces = "application/json",params = {"brand", "search", "priceGte", "priceLte"})
    public ResponseEntity<OblistDtos> global_search(
            @RequestParam Integer _limit,
            @RequestParam (required = false) String brand,
            @RequestParam (required = false)Integer priceGte,
            @RequestParam (required = false)Integer priceLte,
            @RequestParam (required = false)String search
    ) {
        List<Ob> list = service.search_all(brand,priceGte,priceLte,search);
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }

    @GetMapping(value = "/list-ob",produces = "application/json",params = "brand")
    public ResponseEntity<OblistDtos> global_search_1(
            @RequestParam Integer _limit,
            @RequestParam (required = false) String brand
    ) {
        List<Ob> list = service.find_brand(brand);
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }

    @GetMapping(value = "/list-ob",produces = "application/json",params = "search")
    public ResponseEntity<OblistDtos> global_search(
            @RequestParam Integer _limit,
            @RequestParam (required = false)String search
    ) {
        List<Ob> list = service.find_search(search);
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }

    @GetMapping(value = "/list-ob",produces = "application/json",params = {"brand","search"})
    public ResponseEntity<OblistDtos> global_search(
            @RequestParam Integer _limit,
            @RequestParam (required = false)String brand,
            @RequestParam (required = false)String search
    ) {
        List<Ob> list = service.find_brand_search(brand,search);
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }

    @GetMapping(value = "/list-ob",produces = "application/json",params = {"priceGte","search","priceLte"})
    public ResponseEntity<OblistDtos> global_search_0(
            @RequestParam Integer _limit,
            @RequestParam (required = false)String search,
            @RequestParam (required = false)Integer priceGte,
            @RequestParam (required = false)Integer priceLte
    ) {
        List<Ob> list = service.find_search_range(priceGte,priceLte,search);
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }
    @GetMapping(value = "/list-ob",produces = "application/json",params = {"priceGte","brand","priceLte"})
    public ResponseEntity<OblistDtos> global_search_(
            @RequestParam Integer _limit,
            @RequestParam (required = false)String brand,
            @RequestParam (required = false)Integer priceGte,
            @RequestParam (required = false)Integer priceLte
    ) {
        List<Ob> list = service.find_brand_range(brand,priceGte,priceLte);
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }


    @GetMapping(value = "/list-ob",produces = "application/json",params = { "priceGte", "priceLte"})
    public ResponseEntity<OblistDtos> global_search(
            @RequestParam Integer _limit,
            @RequestParam (required = false)Integer priceGte,
            @RequestParam (required = false)Integer priceLte
    ) {
        List<Ob> list = service.find_range_price(priceGte,priceLte);
        return ResponseEntity.ok(OblistDtos.builder().data(list).paginationDto(new PaginationDto(list.size(),_limit)).build());
    }

    @PostMapping(value = "/generate")
    public String generatePdf(
            @RequestBody(required = false) CartDtos request
    ) throws IOException {
        try {
            var cart_id = service_SF.save_SF(request);
            service_SF.save_DT(request,cart_id);
            service.generatePdf(request);
            return "PDF generated successfully.";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error generating PDF.";
        }
    }


    @GetMapping(value = "/list-cart",produces = "application/json")
    public ResponseEntity<?> list_cart(
    ) {
        var list = service_SF.findAll_SF();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/detail-cart",produces = "application/json")
    public ResponseEntity<?> detail_cart(
            @RequestParam (required = false)Integer id
    ) {
        var list = service_SF.findAll_Dt(id);
        return ResponseEntity.ok(list);
    }

}
