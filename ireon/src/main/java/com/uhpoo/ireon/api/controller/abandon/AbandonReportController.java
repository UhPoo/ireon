package com.uhpoo.ireon.api.controller.abandon;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.service.abandon.AbandonReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 유기동물 게시글 신고 API 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/abandon/report")
public class AbandonReportController {

    private final AbandonReportService abandonReportService;

    /**
     * 유기동물 게시글 신고 등록 API
     *
     * @param abandonId 유기동물 게시글 PK
     * @return 등록된 유기동물 게시글 신고 PK
     */
    @PostMapping("/{abandonId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createAbandonReport(@PathVariable(name = "abandonId") Long abandonId) {
        log.debug("AbandonReportController#createAbandonReport called.");
        log.debug("abandonId={}", abandonId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = abandonReportService.createAbandonReport(abandonId, nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }
}
