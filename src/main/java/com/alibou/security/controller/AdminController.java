package com.alibou.security.controller;

import com.alibou.security.model.dto.Ob.ObDtos;
import com.alibou.security.model.dto.Ob.Test;
import com.alibou.security.model.dto.Message;
import com.alibou.security.service.ObService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final ObService service;

    @PostMapping("/add-ob")
    public ResponseEntity<?> save(
            @RequestBody ObDtos request
    ) {
        Boolean response = service.save(request);
        if(response){
            return ResponseEntity.status(201).body(Message.builder().message("Save Product Successfully").status(201).build());
        }else {
            return ResponseEntity.badRequest().body(Message.builder().message("This Product already exist").status(400).build());
        }
    }

    @DeleteMapping("/delete-ob/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().body("Xóa sản phẩm thành công");
    }

    @PutMapping("/update-ob/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody Test request
    ) {
        service.update(id,request);
        return ResponseEntity.ok().body(Message.builder().message("Save Product Successfully").status(201).build());
    }


}
