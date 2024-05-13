package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.service.lost.LostReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 실종동물 게시글 신고 API 컨트롤러
 *
 * @author 최예지
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lost/report")
public class LostReportController {
    private final LostReportService lostReportService;

    /**
     * 실종동물 게시글 신고 등록 API
     *
     * @param lostId 실종동물 게시글 PK
     * @return 등록된 실종동물 게시글 신고 PK
     */
    @PostMapping("/{lostId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createLostReport(@PathVariable(name = "lostId") Long lostId) {
        log.debug("LostReportController#createLostReport called.");
        log.debug("lostId={}", lostId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = lostReportService.createLostReport(lostId, nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }
}
