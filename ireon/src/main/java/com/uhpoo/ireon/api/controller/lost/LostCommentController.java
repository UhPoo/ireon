package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostCommentRequest;
import com.uhpoo.ireon.api.service.lost.dto.LostCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createLostComment(@Valid @RequestPart(name = "request")CreateLostCommentRequest request) {
        log.debug("LostCommentController#createLostComment called.");
        log.debug("CreateLostCommentRequest={}", request);

        //임시 닉네임
        String nickname = "nickname";
        log.debug("nickname={}",  nickname);

        Long saveId = lostCommentService.createLostComment(request.toDto(), nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);

    }
}
