package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostCommentRequest;
import com.uhpoo.ireon.api.controller.lost.request.EditLostCommentRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostCommentResponse;
import com.uhpoo.ireon.api.service.lost.LostCommentQueryService;
import com.uhpoo.ireon.api.service.lost.dto.LostCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 실종동물 댓글 API 컨트롤러
 *
 * @author yekki
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lost/comment")
public class LostCommentController {

    private final LostCommentService lostCommentService;
    private final LostCommentQueryService lostCommentQueryService;

    /**
     * 실종동물 댓글 등록 API
     *
     * @param request 실종동물 댓글 정보
     * @return  등록 된 PK 값
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createLostComment(@Valid @RequestBody CreateLostCommentRequest request) {
        log.debug("LostCommentController#createLostComment called.");
        log.debug("CreateLostCommentRequest={}", request);

        //임시 닉네임
        String nickname = "nickname";
        log.debug("nickname={}",  nickname);

        Long saveId = lostCommentService.createLostComment(request.toDto(), nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 실종동물 댓글 수정 API
     *
     * @param request 수정할 실종동물 댓글 정보
     * @return  등록 된 PK 값
     */
    @PatchMapping
    public ApiResponse<Long> editLostComment(@Valid @RequestBody EditLostCommentRequest request) {
        log.debug("LostCommentController#editeLostComment called.");
        log.debug("EditLostCommentRequest={}", request);

        //임시 닉네임
        String nickname = "nickname";
        log.debug("nickname={}",  nickname);

        Long editId = lostCommentService.editLostComment(request.toDto(), nickname);
        log.debug("saveId={}", editId);

        return ApiResponse.ok(editId);
    }

    /**
     * 실종동물 게시글 삭제 API
     *
     * @param lostCommentId 삭제할 실종동물 게시글 PK
     * @return 삭제 된 PK 값
     */
    @DeleteMapping("/{lostCommentId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Long> deleteCommentLost(@PathVariable(name = "lostCommentId") Long lostCommentId){
        log.debug("LostController#deleteLostComment called.");
        log.debug("lostCommentId={}", lostCommentId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long deleteId = lostCommentService.deleteLostComment(lostCommentId, nickname);
        log.debug("deleteId={}", deleteId);

        return ApiResponse.found(deleteId);
    }
    /**
     * 실종동물 게시글 댓글 목록 조회 API
     *
     * @param lostId     조회하려는 게시글 PK
     * @param lastCommentId 마지막으로 조회된 댓글 PK
     * @return PK에 해당하는 실종돌물 게시글 댓글 목록
     */
    @GetMapping("/{lostId}")
    public ApiResponse<PageResponse<List<LostCommentResponse>>> getLostComment(
            @PathVariable(name = "lostId") Long lostId,
            @RequestParam(name = "lastCommentId", defaultValue = "0") Long lastCommentId) {
        log.debug("LostCommentController#getLostComment called.");
        log.debug("lostId={}", lostId);
        log.debug("lastCommentId={}", lastCommentId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        PageResponse<List<LostCommentResponse>> pageResponse = lostCommentQueryService.getLostComment(lostId, lastCommentId);

        return ApiResponse.ok(pageResponse);
    }
}
