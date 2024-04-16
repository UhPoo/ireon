package com.uhpoo.ireon.api.controller.board;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.board.request.CreateBoardRequest;
import com.uhpoo.ireon.api.controller.board.response.BoardDetailResponse;
import com.uhpoo.ireon.api.controller.board.response.BoardResponse;
import com.uhpoo.ireon.api.service.board.BoardQueryService;
import com.uhpoo.ireon.api.service.board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 자유게시판 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardQueryService boardQueryService;

    /**
     * 자유게시판 게시글 등록 API
     *
     * @param request 등록할 게시글 정보
     * @param file    첨부파일
     * @return 등록된 게시글 PK
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createBoard(@Valid @RequestPart(name = "request") CreateBoardRequest request,
                                         @RequestPart(required = false, name = "file") MultipartFile file) {
        log.debug("BoardController#createBoard called.");
        log.debug("CreateBoardRequest={}", request);
        log.debug("MultipartFile={}", file);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = boardService.createBoard(request.toDto(), nickname, file);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 자유게시판 목록 조회 API
     *
     * @return 검색 조건에 해당하는 자유게시판 게시글 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<List<BoardResponse>>> getBoards() {
        // TODO: 2024-04-16 검색조건 추가
        log.debug("BoardController#getBoards called.");

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        PageResponse<List<BoardResponse>> response = boardQueryService.getBoards(nickname);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 자유게시판 게시글 상세 조회 API
     *
     * @param boardId 조회하려는 게시글 PK
     * @return boardId 에 해당하는 게시글 정보
     */
    @GetMapping("/{boardId}")
    public ApiResponse<BoardDetailResponse> getBoard(@PathVariable(name = "boardId") Long boardId) {
        log.debug("BoardController#getBoard called.");

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        BoardDetailResponse response = boardQueryService.getBoard(boardId, nickname);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
