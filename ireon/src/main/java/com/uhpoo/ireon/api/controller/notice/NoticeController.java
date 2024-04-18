package com.uhpoo.ireon.api.controller.notice;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.notice.request.CreateNoticeRequest;
import com.uhpoo.ireon.api.service.notice.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 공지사항 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createNotice(@Valid @RequestBody CreateNoticeRequest request) {
        log.debug("NoticeController#createNotice called.");
        log.debug("CreateNoticeRequest={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = noticeService.createNotice(request.toDto(), nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }
}
