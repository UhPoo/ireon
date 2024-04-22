package com.uhpoo.ireon.api.controller.abandon;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonCommentRequest;
import com.uhpoo.ireon.api.controller.abandon.request.EditAbandonCommentRequest;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonCommentResponse;
import com.uhpoo.ireon.api.service.abandon.AbandonCommentQueryService;
import com.uhpoo.ireon.api.service.abandon.AbandonCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유기동물 게시판 댓글 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/abandon/comment")
public class AbandonCommentController {

    private final AbandonCommentService commentService;
    private final AbandonCommentQueryService commentQueryService;

    /**
     * 유기동물 게시글 댓글 등록 API
     *
     * @param request 댓글 정보
     * @return 등록된 PK 값
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createComment(@Valid @RequestBody CreateAbandonCommentRequest request) {
        log.debug("AbandonCommentController#createComment called.");
        log.debug("CreateAbandonCommentRequest={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = commentService.createComment(request.toDto(), nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 유기동물 게시글 댓글 목록 조회 API
     *
     * @param abandonId     조회하려는 게시글 PK
     * @param lastCommentId 마지막으로 조회된 댓글 PK
     * @return 유기돌물 게시글 댓글 목록
     */
    @GetMapping("/{abandonId}")
    public ApiResponse<PageResponse<List<AbandonCommentResponse>>> getComment(
            @PathVariable(name = "abandonId") Long abandonId,
            @RequestParam(name = "lastCommentId", defaultValue = "0") Long lastCommentId) {
        log.debug("AbandonCommentController#getComment called.");
        log.debug("abandonId={}", abandonId);
        log.debug("lastCommentId={}", lastCommentId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        PageResponse<List<AbandonCommentResponse>> pageResponse = commentQueryService.getComment(abandonId, lastCommentId);

        return ApiResponse.ok(pageResponse);
    }

    /**
     * 유기동물 댓글 수정 API
     *
     * @param request 수정할 댓글 정보
     * @return 수정된 PK 값
     */
    @PatchMapping
    public ApiResponse<Long> editComment(@Valid EditAbandonCommentRequest request) {
        log.debug("AbandonCommentController#editComment called.");
        log.debug("request={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long editId = commentService.editComment(request.toDto(), nickname);
        log.debug("editId={}", editId);

        return ApiResponse.ok(editId);
    }

    /**
     * 유기동물 댓글 삭제 API
     *
     * @param commentId 삭제할 댓글 PK 값
     * @return 삭제된 댓글 PK 값
     */
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Long> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        log.debug("AbandonCommentController#deleteComment called.");
        log.debug("commentId={}", commentId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long deleteId = commentService.deleteComment(commentId, nickname);
        log.debug("deleteId={}", deleteId);

        return ApiResponse.found(deleteId);
    }
}
