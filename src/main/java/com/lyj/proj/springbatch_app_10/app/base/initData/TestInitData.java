package com.lyj.proj.springbatch_app_10.app.base.initData;

import com.lyj.proj.springbatch_app_10.app.cart.service.CartService;
import com.lyj.proj.springbatch_app_10.app.member.service.MemberService;
import com.lyj.proj.springbatch_app_10.app.order.service.OrderService;
import com.lyj.proj.springbatch_app_10.app.product.service.ProductService;
import com.lyj.proj.springbatch_app_10.app.song.service.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//.yml의 Profile이 test일 때 초기 데이터 생성
@Configuration
@Profile("test")
public class TestInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService,
                               SongService songService,
                               ProductService productService,
                               CartService cartService,
                               OrderService orderService) {
        return args -> {
            before(memberService, songService, productService, cartService, orderService);
        };
    }
}