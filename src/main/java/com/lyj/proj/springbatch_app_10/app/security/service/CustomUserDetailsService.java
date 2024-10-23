package com.lyj.proj.springbatch_app_10.app.security.service;

import com.lyj.proj.springbatch_app_10.app.member.entity.Member;
import com.lyj.proj.springbatch_app_10.app.member.repository.MemberRepository;
import com.lyj.proj.springbatch_app_10.app.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor //final에 대한 생성자를 자동으로 생성
public class CustomUserDetailsService implements UserDetailsService {
    //이 클래스는? 원래 있는 UserDetailService를 Custom해서 쓰기 위함
    //사용자 인증 정보 조회(데이터 출처는 DB) -> 불러와서 Spring Security에서 사용 가능하게 변환

    //final == final 키워드를 가진 채로 등장하면, 이 객체는 '불변성'을 띠게 된다.
    //객체 상태가 변하지 않는다.
    private final MemberRepository memberRepository;

    //오버라이드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Username을 기반으로 사용자가 있는지 없는지 확인하여 갖다 앉혀놓는다.
        Member member = memberRepository.findByUsername(username).get();

        //사용자가 있으면, GrantedAuthority == 권한을 받다. ==> MEMBER '권한'을 부여.
        //상위 멤버가 있다거나, Admin이 따로 있다거나 하는 식으로 권한의 형태가 달라질 수 있음
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        //MemberContext 객체로 만들어서 줘버림
        return new MemberContext(member, authorities);
    }
}
