package com.uhpoo.ireon.api.service.board;

import com.uhpoo.ireon.api.service.board.dto.CreateBoardDto;
import com.uhpoo.ireon.api.service.board.dto.EditBoardDto;
import com.uhpoo.ireon.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 자유게시판 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 자유게시판 게시글 등록
     *
     * @param dto      등록할 게시글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @param file     첨부파일
     * @return 등록된 게시글 PK
     */
    public Long createBoard(CreateBoardDto dto, String nickname, MultipartFile file) {
        return null;
    }

    /**
     * 자유게시판 게시글 수정
     *
     * @param dto      수정할 게시글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @param file     첨부파일
     * @return 수정된 게시글 PK
     */
    public Long editBoard(EditBoardDto dto, String nickname, MultipartFile file) {
        return null;
    }

    /**
     * 자유게시판 게시글 삭제
     *
     * @param boardId  삭제할 게시글 PK
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 삭제된 게시글 PK
     */
    public Long deleteBoard(Long boardId, String nickname) {
        return null;
    }
}
