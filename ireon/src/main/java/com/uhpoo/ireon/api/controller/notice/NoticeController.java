package com.uhpoo.ireon.api.controller.notice;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.notice.request.CreateNoticeRequest;
import com.uhpoo.ireon.api.controller.notice.request.EditNoticeRequest;
import com.uhpoo.ireon.api.controller.notice.response.NoticeDetailResponse;
import com.uhpoo.ireon.api.controller.notice.response.NoticeResponse;
import com.uhpoo.ireon.api.service.notice.NoticeQueryService;
import com.uhpoo.ireon.api.service.notice.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final NoticeQueryService noticeQueryService;

    /**
     * 공지사항 등록 API
     *
     * @param request 등록할 공지사항 정보
     * @return 등록된 공지사항 PK
     */
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

    /**
     * 공지사항 목록 조회 API
     *
     * @param lastNoticeId 마지막으로 조회된 공지사항 PK
     * @return 공지사항 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<List<NoticeResponse>>> getNotices(
            @RequestParam(name = "lastNoticeId", defaultValue = "0") Long lastNoticeId) {
        // TODO: 2024-04-18 검색조건 추가
        log.debug("NoticeController#getNotices called.");
        log.debug("lastNoticeId={}", lastNoticeId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        PageResponse<List<NoticeResponse>> response = noticeQueryService.getNotices(lastNoticeId);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 공지사항 상세 조회 API
     *
     * @param noticeId 조회할 공지사항 PK
     * @return 공지사항 상세 정보
     */
    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeDetailResponse> getNotice(@PathVariable(name = "noticeId") Long noticeId) {
        log.debug("NoticeController#getNotice called.");
        log.debug("noticeId={}", noticeId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        NoticeDetailResponse response = noticeQueryService.getNotice(noticeId, nickname);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    @PatchMapping
    public ApiResponse<Long> editNotice(@Valid @RequestBody EditNoticeRequest request) {
        log.debug("NoticeController#editNotice called.");
        log.debug("EditNoticeRequest={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long editId = noticeService.editNotice(request.toDto(), nickname);
        log.debug("editId={}", editId);

        return ApiResponse.ok(editId);
    }
}
