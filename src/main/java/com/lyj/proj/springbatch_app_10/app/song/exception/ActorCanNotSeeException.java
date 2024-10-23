package com.lyj.proj.springbatch_app_10.app.song.exception;

public class ActorCanNotSeeException extends RuntimeException {
}

/*
A
음원 1
음원 2 => 노래 1, 20,000 -> 상품 1
음원 3
음원 4 => 노래 2, 200,000 -> 상품 2

B
음원 5
음원 6 => 노래 3, 10,000 -> 상품 3
음원 7
음원 8 => 노래 4, 150,000 -> 상품 4

내 음원은 나만 볼 수 있다.
내 상품은 남들도 볼 수 있다.
본인이 만든 음원을 상품화
- 내가 만든 상품은 내 장바구니에 담을 수 없다.
- 색상, 사이즈, 갯수 X
- Cash 충전금
- 정산
 */