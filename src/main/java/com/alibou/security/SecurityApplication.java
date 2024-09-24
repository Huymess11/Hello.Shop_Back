package com.alibou.security;

import com.alibou.security.model.dto.CartDtos;
import com.alibou.security.model.dto.Ob.ObDtos;
import com.alibou.security.model.dto.global.Cart_Info;
import com.alibou.security.model.dto.global.Cart_Product;
import com.alibou.security.service.AuthenticationService;
import com.alibou.security.model.dto.global.RegisterDtos;
import com.alibou.security.service.CartSFService;
import com.alibou.security.service.ObService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service,
			ObService observice,
			CartSFService cartSFService
	) {
		return args -> {


			var admin = RegisterDtos.builder()
					.fullName("Admin")
					.username("Admin")
					.email("admin@gmail.com")
					.password("Admin")
					.build();
			System.out.println("Admin token: " + service.createAdmin(admin));

			var user = RegisterDtos.builder()
					.fullName("Huymess")
					.username("Huymess11")
					.email("user@gmail.com")
					.password("password")
					.build();
			System.out.println("User token: " + service.register(user).getJwt());


			var ob = ObDtos.builder()
					.name("Samsung Galaxy Z Fold6")
					.price(12999)
					.brand("Phone")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-z-fold-6-xanh_5_.png")

					.description("The Rolex Submariner is a classic diving watch, known for its durability and precision. It features a black dial, a rotating bezel, and a steel bracelet.")
					.build();
			var ob1 = ObDtos.builder()
					.name("MacBook Air M1")
					.price(25999)
					.brand("Laptop")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/macbook-air-gold-select-201810_4_3_1_1_1_1.jpg")
					.description("The MacBook Air M1 features a stunning Retina display and is powered by the Apple M1 chip, offering powerful performance in a sleek design.")
					.build();

			var ob2 = ObDtos.builder()
					.name("Samsung Galaxy S21 Ultra")
					.price(30999)
					.brand("Phone")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/a/samsung-galaxy-s21-ultra.png")
					.description("The Samsung Galaxy S21 Ultra is a high-end smartphone with a dynamic AMOLED 2X display, a powerful Exynos 2100 processor, and a 108MP camera.")
					.build();

			var ob3 = ObDtos.builder()
					.name("Dell UltraSharp U2720Q")
					.price(10999)
					.brand("Monitor")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_179_15_.png")
					.description("The Dell UltraSharp U2720Q is a 27-inch 4K monitor, offering outstanding color accuracy and sharpness, perfect for professional use.")
					.build();

			var ob4 = ObDtos.builder()
					.name("Logitech G502 Hero")
					.price(1599)
					.brand("Mouse")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/h/chuot-choi-game-co-day-logitech-g502-hero.png")
					.description("The Logitech G502 Hero is a high-performance gaming mouse featuring a 16,000 DPI sensor and customizable RGB lighting.")
					.build();

			var ob5 = ObDtos.builder()
					.name("Corsair K95 RGB Platinum")
					.price(4999)
					.brand("Keyboard")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/r/a/ram-corsair-dominator-platinum-rgb-ddr5-5200mhz-32gb_4_.png")
					.description("The Corsair K95 RGB Platinum is a mechanical gaming keyboard with Cherry MX switches, dynamic RGB backlighting, and six dedicated macro keys.")
					.build();

			var ob6 = ObDtos.builder()
					.name("HP Spectre x360")
					.price(37999)
					.brand("Laptop")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-hp-spectre-x360-14-ef0030tu-6k773pa-cu-dep-1_3.png")
					.description("The HP Spectre x360 is a 2-in-1 convertible laptop, featuring a 4K touchscreen display and powered by an Intel Core i7 processor.")
					.build();

			var ob7 = ObDtos.builder()
					.name("iPhone 13 Pro Max")
					.price(32999)
					.brand("Phone")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/3/_/3_51_1_2_2_1_1_2.jpg")
					.description("The iPhone 13 Pro Max features a Super Retina XDR display, the A15 Bionic chip, and an advanced triple-camera system for stunning photos.")
					.build();

			var ob8 = ObDtos.builder()
					.name("LG UltraGear 27GN950-B")
					.price(18999)
					.brand("Monitor")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/man-hinh-lg-ultragear-oled-27gr95qe-b-27-inch.png")
					.description("The LG UltraGear 27GN950-B is a 27-inch gaming monitor with a 4K Nano IPS display, 144Hz refresh rate, and 1ms response time.")
					.build();

			var ob9 = ObDtos.builder()
					.name("Razer DeathAdder V2")
					.price(1299)
					.brand("Mouse")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0002_61lwayoulwl._ac_sl1500.jpg")
					.description("The Razer DeathAdder V2 is an ergonomic gaming mouse with a 20,000 DPI sensor, lightweight design, and Razer Chroma RGB lighting.")
					.build();

			var ob10 = ObDtos.builder()
					.name("Razer Huntsman Elite")
					.price(5999)
					.brand("Keyboard")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0003_chu_t_razer_basilisk_v3.jpg")
					.description("The Razer Huntsman Elite is a premium gaming keyboard featuring Razer Optical Switches, RGB lighting, and a magnetic wrist rest for comfort.")
					.build();

			var ob11 = ObDtos.builder()
					.name("Dell XPS 13")
					.price(32999)
					.brand("Laptop")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_6__7.png")
					.description("The Dell XPS 13 is a premium ultrabook with a 13.4-inch InfinityEdge display and powered by the latest Intel Core i7 processor.")
					.build();

			var ob12 = ObDtos.builder()
					.name("OnePlus 9 Pro")
					.price(26999)
					.brand("Phone")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/o/n/oneplus-9_1.jpg")
					.description("The OnePlus 9 Pro offers a 120Hz Fluid AMOLED display, Snapdragon 888 chipset, and Hasselblad-tuned cameras for incredible photography.")
					.build();

			var ob13 = ObDtos.builder()
					.name("Samsung Odyssey G7")
					.price(14999)
					.brand("Monitor")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/v/n/vn-curved-c32g75tqse-lc32g75tqse.png")
					.description("The Samsung Odyssey G7 is a 32-inch curved gaming monitor with a 240Hz refresh rate and QLED display for immersive gaming experiences.")
					.build();

			var ob14 = ObDtos.builder()
					.name("SteelSeries Rival 600")
					.price(1999)
					.brand("Mouse")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_266_1_1.png")
					.description("The SteelSeries Rival 600 is a dual-sensor gaming mouse with adjustable weight tuning and customizable RGB lighting.")
					.build();

			var ob15 = ObDtos.builder()
					.name("HyperX Alloy Origins")
					.price(2999)
					.brand("Keyboard")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/f/i/file_3_5.jpg")
					.description("The HyperX Alloy Origins is a mechanical gaming keyboard featuring HyperX Red switches, durable aluminum body, and customizable RGB lighting.")
					.build();

			var ob16 = ObDtos.builder()
					.name("Lenovo ThinkPad X1 Carbon")
					.price(41999)
					.brand("Laptop")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_-_2023-05-17t235249.905.png")
					.description("The Lenovo ThinkPad X1 Carbon is a lightweight business laptop with a 14-inch 4K display, 11th Gen Intel Core i7 processor, and long battery life.")
					.build();

			var ob17 = ObDtos.builder()
					.name("Google Pixel 6")
					.price(22999)
					.brand("Phone")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/g/gggg_1__1.jpg")
					.description("The Google Pixel 6 features Google's Tensor chip, an advanced camera system with AI-powered computational photography, and a sleek design.")
					.build();

			var ob18 = ObDtos.builder()
					.name("ASUS ProArt PA278QV")
					.price(13999)
					.brand("Monitor")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/0/1/01_3.png")
					.description("The ASUS ProArt PA278QV is a professional-grade monitor with a 27-inch WQHD display, offering excellent color accuracy for creative professionals.")
					.build();

			var ob19 = ObDtos.builder()
					.name("Logitech MX Master 3")
					.price(2999)
					.brand("Mouse")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/s/f/sfeftet466554.jpg")
					.description("The Logitech MX Master 3 is a wireless mouse designed for productivity, featuring a precision scroll wheel, ergonomic design, and long battery life.")
					.build();

			var ob20 = ObDtos.builder()
					.name("Bàn phím cơ Dareu EK75 trắng đen")
					.price(4599)
					.brand("Keyboard")
					.pictureURL("https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/b/a/ban-phim-co-dareu-ek75-dream-switch.png")
					.description("The Roccat Vulcan 121 is a mechanical gaming keyboard with Titan Switches, RGB lighting, and a sleek, low-profile design for maximum performance.")
					.build();




			observice.save(ob20);
			observice.save(ob);
			observice.save(ob1);
			observice.save(ob2);
			observice.save(ob3);
			observice.save(ob4);
			observice.save(ob5);
			observice.save(ob6);
			observice.save(ob7);
			observice.save(ob8);
			observice.save(ob9);
			observice.save(ob10);
			observice.save(ob11);
			observice.save(ob12);
			observice.save(ob13);
			observice.save(ob14);
			observice.save(ob15);
			observice.save(ob16);
			observice.save(ob17);
			observice.save(ob18);


			System.out.println("Ob name: " + ob.toString());

			var info = Cart_Info.builder()
					.code_Bill("Invoice_RK35623")
					.email("Dan646@gmail.com").name("Dan")
					.telephoneNumber("0345345345")
					.address_to("Thanh Xuan, Ha Noi")
					.build();
			var list = new ArrayList<Cart_Product>();
			list.add(new Cart_Product(1,1,"Rolex Submariner","https://i.pinimg.com/564x/60/95/1c/60951c6acf113c84b9a87802e1c8fb7c.jpg",12999.0f,12999.0f));
			list.add(new Cart_Product(2,4,"Calvin Klein Quartz","https://i.pinimg.com/564x/60/95/1c/60951c6acf113c84b9a87802e1c8fb7c.jpg",299.0f,598.0f));
			list.add(new Cart_Product(1,5,"Longines Conquest","https://i.pinimg.com/564x/60/95/1c/60951c6acf113c84b9a87802e1c8fb7c.jpg",599.0f,599.0f));
			var cart_demo = CartDtos.builder().info(info).cart_list(list).build();
			var cart_id = cartSFService.save_SF(cart_demo);
			cartSFService.save_DT(cart_demo,cart_id);

			System.out.println("Invoice Demo successfully" );
		};
	}
}
