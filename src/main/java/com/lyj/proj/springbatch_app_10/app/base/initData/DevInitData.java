package com.lyj.proj.springbatch_app_10.app.base.initData;

import com.lyj.proj.springbatch_app_10.app.member.service.MemberService;
import com.lyj.proj.springbatch_app_10.app.song.service.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


//.yml의 Profile이 dev일 때 초기 데이터 생성
@Configuration
@Profile("dev")
public class DevInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService, SongService songService) {
        return args -> {
            before(memberService, songService);
        };
    }
}
//application.yml에서 한글 깨져서 필기 옮김
//# application.yml => 기본 설정
//# application_dev.yml => 개발 모드의 설정
//# application_prod.yml => 운영 모드의 설정
//# application_test.yml => 테스트 모드의 설정
//profiles:
//active: dev << 이런 식으로 해두면 모드 중에 하나를 선택해서 덮어쓰게 할 수 있음
