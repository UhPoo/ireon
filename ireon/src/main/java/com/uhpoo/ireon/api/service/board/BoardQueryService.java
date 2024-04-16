package com.uhpoo.ireon.api.service.board;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.board.response.BoardResponse;
import com.uhpoo.ireon.domain.board.repository.BoardQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 자유게시판 조회 서비스
 *
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardQueryService {

    private final BoardQueryRepository boardQueryRepository;

    /**
     * 자유게시판 게시글 목록 조회
     *
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 검색 조건에 해당하는 게시글 목록
     */
    public PageResponse<List<BoardResponse>> getBoards(String nickname) {
        return null;
    }
}
