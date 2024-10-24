package com.lyj.proj.springbatch_app_10.app.product.entity;

import com.lyj.proj.springbatch_app_10.app.base.entity.BaseEntity;
import com.lyj.proj.springbatch_app_10.app.member.entity.Member;
import com.lyj.proj.springbatch_app_10.app.song.entity.Song;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED) // 아무런 매개변수가 없는 생성자를 생성, 다른 패키지에 소속된 클래스는 접근 불허 -> 보안상 좋다
public class Product extends BaseEntity {
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    @ManyToOne(fetch = FetchType.LAZY)
    private Song song;
    private int price;

    public Product(long id) {
        super(id);
    }

    public int getSalePrice() {
        return getPrice();
    }

    public int getWholesalePrice() {
        return (int) Math.ceil(getPrice() * 0.7);
    }

    public boolean isOrderable() {
        return true;
    }
}