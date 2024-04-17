package com.uhpoo.ireon.api.controller.board;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.board.request.CreateBoardCommentRequest;
import com.uhpoo.ireon.api.controller.board.response.BoardCommentResponse;
import com.uhpoo.ireon.api.service.board.BoardCommentQueryService;
import com.uhpoo.ireon.api.service.board.BoardCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 자유게시판 댓글 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comment")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;
    private final BoardCommentQueryService boardCommentQueryService;

    /**
     * 자유게시판 댓글 등록 API
     *
     * @param request 댓글 정보
     * @return 등록된 댓글 PK
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createBoarComment(@Valid @RequestBody CreateBoardCommentRequest request) {
        log.debug("BoardCommentController#createBoarComment called.");
        log.debug("CreateBoardCommentRequest={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = boardCommentService.createBoardComment(request.toDto(), nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 자유게시판 댓글 목록 조회 API
     *
     * @param boardId            조회할 댓글의 게시글 PK
     * @param lastBoardCommentId 마지막으로 조회한 댓글 PK
     * @return 댓글 목록
     */
    @GetMapping("/{boardId}")
    public ApiResponse<PageResponse<List<BoardCommentResponse>>> getBoardComments(
            @PathVariable(name = "boardId") Long boardId,
            @RequestParam(name = "lastBoardCommentId", defaultValue = "0") Long lastBoardCommentId) {

        log.debug("AbandonCommentController#getComment called.");
        log.debug("boardId={}", boardId);
        log.debug("lastBoardCommentId={}", lastBoardCommentId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        PageResponse<List<BoardCommentResponse>> response =
                boardCommentQueryService.getComments(boardId, lastBoardCommentId);

        return ApiResponse.ok(response);
    }
}
