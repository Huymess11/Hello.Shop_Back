package com.alibou.security.service;

import com.alibou.security.exception.ObjectNotFoundException;
import com.alibou.security.model.dto.CartDtos;
import com.alibou.security.model.dto.Ob.ObDtos;
import com.alibou.security.model.dto.Ob.Test;
import com.alibou.security.model.dto.global.Cart_Product;
import com.alibou.security.model.entities.Ob;
import com.alibou.security.repositories.ObRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import com.codingerror.model.AddressDetails;
import com.codingerror.model.HeaderDetails;
import com.codingerror.model.Product;
import com.codingerror.model.ProductTableHeader;
import com.codingerror.service.CodingErrorPdfInvoiceCreator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ObService {

    private final ObRepository repository;

    public Boolean save(ObDtos request) {

        var ob = repository.findByName(request.getName());

        if(ob.isPresent()){
            return false;
        }

        var ob_flag = Ob.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .description(request.getDescription())
                .price(request.getPrice())
                .pictureURL(request.getPictureURL())
                .createDate(new Date())
                .createdByUserid(1)
                .build();

        repository.save(ob_flag);
        return true;
    }

    public List<Ob> findAll() {
        return repository.findAll();
    }

    public Optional<Ob> findbyID(Integer id){return repository.findById(id);}

    public void update(Integer id, Test request) {
        // Kiểm tra xem đối tượng có tồn tại không
        Optional<Ob> existingBookOptional = repository.findById(id);
        if (existingBookOptional.isPresent()) {
            Ob existingBook = existingBookOptional.get();

            // Cập nhật thông tin từ request
            existingBook.setName(request.getName());
            existingBook.setBrand(request.getBrand());
            existingBook.setDescription(request.getDescription());
            existingBook.setPrice(request.getPrice());
            existingBook.setPictureURL(request.getPictureURL());

            // Cập nhật ngày tạo mới
            existingBook.setCreateDate(new Date());

            // Lưu đối tượng đã cập nhật vào repository
            repository.save(existingBook);
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng cần cập nhật
            throw new ObjectNotFoundException("Không tìm thấy đối tượng với id: " + id);
        }
    }
    public void delete(Integer id) {
        // Kiểm tra xem đối tượng có tồn tại không
        Optional<Ob> existingBookOptional = repository.findById(id);
        if (existingBookOptional.isPresent()) {
            // Nếu đối tượng tồn tại, thực hiện xóa
            repository.deleteById(id);
            System.out.println("Xóa sản phẩm thành công");
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng cần xóa
            throw new ObjectNotFoundException("Không tìm thấy đối tượng với id: " + id);
        }
    }


    public List<Ob> search_all(String brand,Integer priceGte,Integer priceLte,String search){
        return repository.search_all(brand,priceGte,priceLte,search);
    }

    public List<Ob> find_search(String search){
        return repository.find_search(search);
    }

    public List<Ob> find_brand(String brand){
        return repository.find_brand(brand);
    }

    public List<Ob> find_range_price(Integer priceGte, Integer priceLte){
        return repository.find_range_price(priceGte,priceLte);
    }

    public List<Ob> find_search_range(Integer priceGte, Integer priceLte,String search){
        return repository.find_search_range(priceGte,priceLte,search);
    }

    public List<Ob> find_brand_range(String brand,Integer priceGte, Integer priceLte){
        return repository.find_brand_range(brand,priceGte,priceLte);
    }

    public List<Ob> find_brand_search(String brand,String search){
        return repository.find_brand_search(brand,search);
    }

//    public List<Ob> find_brand(String key){
//        return repository.search(key);
//    }

//    public byte[] generatePdf() throws FileNotFoundException {
////        LocalDate ld = LocalDate.now();
//        String pdfName = "D:\\invoice_test\\test" + ".pdf";
//        CodingErrorPdfInvoiceCreator cepdf = new CodingErrorPdfInvoiceCreator(pdfName);
//        cepdf.createDocument();
//
//        // Create Header start
//        HeaderDetails header = new HeaderDetails();
//        header.setInvoiceNo("RK35623").setInvoiceDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build();
//        cepdf.createHeader(header);
//
//        // Create Address start
//        AddressDetails addressDetails = new AddressDetails();
//        addressDetails
//                .setBillingCompany("Coding Error")
//                .setBillingName("Bhaskar")
//                .setBillingAddress("Bangluru,karnataka,india\n djdj\ndsjdsk")
//                .setBillingEmail("codingerror303@gmail.com")
//                .setShippingName("Customer Name \n")
//                .setShippingAddress("Banglore Name sdss\n swjs\n")
//                .build();
//
//        cepdf.createAddress(addressDetails);
//
//        // Product Start
//        ProductTableHeader productTableHeader = new ProductTableHeader();
//        cepdf.createTableHeader(productTableHeader);
//        List<Product> productList = cepdf.getDummyProductList();
//        productList = cepdf.modifyProductList(productList);
//        cepdf.createProduct(productList);
//
//        // Term and Condition Start
//        cepdf.createTnc(getTncList(), false, "src/main/resources/img.png");
//
//        System.out.println("pdf generated");
//        return new byte[0];
//    }
//
//    private List<String> getTncList() {
//        List<String> TncList = new ArrayList<>();
//        TncList.add("1. The Seller shall not be liable to the Buyer directly or indirectly for any loss or damage suffered by the Buyer.");
//        TncList.add("2. The Seller warrants the product for one (1) year from the date of shipment");
//        return TncList;
//    }
    public void generatePdf(CartDtos request) throws FileNotFoundException {
        //Lấy thông tin từ request, Info đầu tiền
        var code_bill = request.getInfo().getCode_Bill();
        var email = request.getInfo().getEmail();
        var name = request.getInfo().getName();
        var telephone = request.getInfo().getTelephoneNumber();
//        var city = request.getInfo().getCity();
        var address_to = request.getInfo().getAddress_to();

        // lấy thông tin list product
        List<Cart_Product> list = request.getCart_list();

        String pdfName = "D:\\Demo Invoice\\Invoice_RX" +code_bill + ".pdf";
        CodingErrorPdfInvoiceCreator cepdf = new CodingErrorPdfInvoiceCreator(pdfName);
        cepdf.createDocument();

        // Create Header start
        HeaderDetails header = new HeaderDetails();
        header.setInvoiceNo("Invoice_RX"+code_bill).setInvoiceDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build();
        cepdf.createHeader(header);

        // Create Address start
        AddressDetails addressDetails = new AddressDetails();
        addressDetails
                .setBillingCompany("Telephone Number: " + telephone)
                .setBillingName(name)
                .setBillingAddress(address_to)
                .setBillingEmail(email)
                .setShippingName(name)
                .setShippingAddress(address_to)
                .build();

        cepdf.createAddress(addressDetails);

        // Product Start
        ProductTableHeader productTableHeader = new ProductTableHeader();
        cepdf.createTableHeader(productTableHeader);
        List<Product> productList = new ArrayList<>();

        for (var element : list){
            productList.add(new Product(element.getName(),element.getCount(),element.getTotalPrice()));
        }
//        productList.add(new Product("Casio-2100RB-3B",2,2999));
//        productList.add(new Product("Casio-3C",5,500));
//        productList.add(new Product("GA-2100RB-3D",2,159));
        productList = cepdf.modifyProductList(productList);
        cepdf.createProduct(productList);

        // Term and Condition Start
        cepdf.createTnc(getTncList(), false, "src/main/resources/img.png");

        System.out.println("pdf generated");
}

    private List<String> getTncList() {
        List<String> TncList = new ArrayList<>();
        TncList.add("1. The Seller shall not be liable to the Buyer directly or indirectly for any loss or damage suffered by the Buyer.");
        TncList.add("2. The Seller warrants the product for one (1) year from the date of shipment");
        return TncList;
    }
}
