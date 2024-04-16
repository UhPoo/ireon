package com.uhpoo.ireon.api.controller.abandon;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonCommentRequest;
import com.uhpoo.ireon.api.service.abandon.AbandonCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
