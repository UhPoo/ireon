package com.uhpoo.ireon.api.controller.abandon;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.abandon.request.AbandonScrapRequest;
import com.uhpoo.ireon.api.service.abandon.AbandonScrapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유기동물 스크랩 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/abandon/scrap")
public class AbandonScrapController {

    private final AbandonScrapService abandonScrapService;

    /**
     * 유기동물 스크랩 등록 / 취소 API
     * 이미 스크랩한 경우 스크랩을 취소하고 스크랩하지 않았으면 스크랩을 새롭게 등록
     *
     * @param request 유기동물 게시글 PK
     * @return 등록 / 취소된 스크랩 PK
     */
    @PostMapping
    public ApiResponse<Long> scrap(@Valid @RequestBody AbandonScrapRequest request) {
        log.debug("AbandonScrapController#scrap called.");
        log.debug("AbandonScrapRequest={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long scrapId = abandonScrapService.scrap(request.toDto(), nickname);
        log.debug("scrapId={}", scrapId);

        return ApiResponse.ok(scrapId);
    }
}
