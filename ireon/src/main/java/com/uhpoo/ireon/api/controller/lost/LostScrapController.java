package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.lost.request.AddLostScrapRequest;
import com.uhpoo.ireon.api.service.lost.LostScrapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    private LostScrapService lostScrapService;

    /**
     * 실종동물 스크랩 추가 API
     *
     * @param request 실종동물 스크랩 게시글 정보
     * @return  등록 된 PK 값
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> addLostScrap(@Valid @RequestBody AddLostScrapRequest request) {
        log.debug("LostScrapController#addLostScrap called.");
        log.debug("AddLostScrapRequest={}", request);

        Long addId = lostScrapService.addLostScrap(request.toDto());
        log.debug("addId={}", addId);

        return ApiResponse.created(addId);
    }
}
