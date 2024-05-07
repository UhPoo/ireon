package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.request.AddLostScrapRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostResponse;
import com.uhpoo.ireon.api.service.lost.LostQueryService;
import com.uhpoo.ireon.api.service.lost.LostScrapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 실종동물 스크랩 API 컨트롤러
 *
 * @author yekki
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lost/scrap")
public class LostScrapController {

    private final LostScrapService lostScrapService;
    private final LostQueryService lostQueryService;

    /**
     * 실종동물 스크랩 등록 / 취소 API
     * 이미 스크랩한 경우 스크랩을 취소하고 스크랩하지 않았으면 스크랩을 새롭게 등록
     *
     * @param request 실종동물 스크랩 게시글 PK
     * @return  등록/취소 된 PK 값
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> addLostScrap(@Valid @RequestBody AddLostScrapRequest request) {
        log.debug("LostScrapController#addLostScrap called.");
        log.debug("AddLostScrapRequest={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long scrapId = lostScrapService.scrap(request.toDto());
        log.debug("scrapId={}", scrapId);

        return ApiResponse.created(scrapId);
    }

    /**
     * 스크랩한 실종동물 게시글 목록 조회 API
     *
     * @return 스크랩한 실종동물 게시글 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<List<LostResponse>>> getLostScraps() {
        log.debug("LostScrapController#getLostScrap called.");

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        PageResponse<List<LostResponse>> response = lostQueryService.getLostScraps(nickname);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
