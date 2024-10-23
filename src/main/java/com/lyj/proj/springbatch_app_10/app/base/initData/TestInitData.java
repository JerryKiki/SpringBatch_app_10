package com.lyj.proj.springbatch_app_10.app.base.initData;

import com.lyj.proj.springbatch_app_10.app.member.service.MemberService;
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
    CommandLineRunner initData(MemberService memberService, SongService songService) {
        return args -> {
            before(memberService, songService);
        };
    }
}
