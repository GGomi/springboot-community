package com.essri.community.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum BoardType {
    notice("공지사항"),
    free("자유게시판");

    private String value;Å

    @Builder
    BoardType(String value) {
        this.value = value;
    }
}
