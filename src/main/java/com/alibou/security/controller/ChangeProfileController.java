package com.alibou.security.controller;

import com.alibou.security.model.dto.CartDtos;
import com.alibou.security.model.dto.ChangePasswordDtos;
import com.alibou.security.model.dto.ChangeProfileDtos;
import com.alibou.security.service.CartSFService;
import com.alibou.security.service.ObService;
import com.alibou.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class ChangeProfileController {

    private final UserService Change_service;

    private final ObService service;

    private final CartSFService service_SF;


    @PatchMapping("/change-profile")
    public ResponseEntity<?> changeProfile(
            @RequestBody ChangeProfileDtos request,
            Principal connectedUser
    ) {
         Change_service.changeProfile(request, connectedUser);
         return ResponseEntity.ok("changeProfile successfully !!!");
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordDtos request,
            Principal connectedUser
    ) {
        var bol = Change_service.changePassword(request, connectedUser);
        if(bol.equals("changePassword successfully !!!")){
            return ResponseEntity.status(200).body("changePassword successfully !!!");
        }else{
            return ResponseEntity.status(405).body(bol);
        }
    }

//    @PostMapping(value = "/generate",produces = "application/json")
//    public String generatePdf(
//            @RequestBody CartDtos request
//    ) throws IOException {
//        try {
//            var cart_id = service_SF.save_SF(request);
//            service_SF.save_DT(request,cart_id);
//            service.generatePdf(request);
//            return "PDF generated successfully.";
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return "Error generating PDF.";
//        }
//    }
}
