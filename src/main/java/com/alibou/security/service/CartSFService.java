package com.alibou.security.service;

import com.alibou.security.model.dto.CartDtos;
import com.alibou.security.model.dto.Ob.ObDtos;
import com.alibou.security.model.entities.CartDt;
import com.alibou.security.model.entities.CartSF;
import com.alibou.security.model.entities.Ob;
import com.alibou.security.repositories.CartDtRepository;
import com.alibou.security.repositories.CartSFRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartSFService {
    private final CartDtRepository repository_Dt;
    private final CartSFRepository repository_Sf;

    public Integer save_SF(CartDtos request) {

        var cart = repository_Sf.findByCodeBill(request.getInfo().getCode_Bill());

        if(cart.isPresent()){
            return -1;
        }

//        var ob_flag = Ob.builder()
//                .name(request.getName())
//                .brand(request.getBrand())
//                .color(request.getColor())
//                .country(request.getCountry())
//                .description(request.getDescription())
//                .price(request.getPrice())
//                .pictureURL(request.getPictureURL())
//                .createDate(new Date())
//                .build();
        var list = request.getCart_list();
        Float total = 0.0f;
        for (var x: list) {
            total = total + x.getTotalPrice();
        }
        var cart_flag = CartSF.builder()
                .codeBill(request.getInfo().getCode_Bill())
                .email(request.getInfo().getEmail())
                .name(request.getInfo().getName())
                .getTelephoneNumber(request.getInfo().getTelephoneNumber())
                .address_to(request.getInfo().getAddress_to())
                .number_cartList(request.getCart_list().size())
                .totalPrice(total).build();

        repository_Sf.save(cart_flag);
        return cart_flag.getId();
    }

    public void save_DT(CartDtos request, Integer cart_id) {

//        var cart = repository_Dt.findById(cart_id);
//        if(!cart.isPresent()){
//            return ;
//        }
        var list = request.getCart_list();

        for (var x : list) {
            var cart_flag = CartDt.builder()
                    .cart_id(cart_id)
                    .count(x.getCount())
                    .name(x.getName())
                    .imgURL(x.getImgURL())
                    .unitPrice(x.getUnitPrice())
                    .totalPrice(x.getTotalPrice())
                    .build();
            repository_Dt.save(cart_flag);
        }
        System.out.println("save DT");
    }
    public List<CartSF> findAll_SF() {
        return repository_Sf.findAll();
    }
    public List<CartDt> findAll_Dt(Integer cart_id) {
        return repository_Dt.findByCart_id(cart_id);
    }

}
