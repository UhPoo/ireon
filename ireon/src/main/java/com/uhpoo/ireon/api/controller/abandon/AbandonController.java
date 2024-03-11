package com.uhpoo.ireon.api.controller.abandon;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonRequest;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonDetailResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonResponse;
import com.uhpoo.ireon.api.service.abandon.AbandonQueryService;
import com.uhpoo.ireon.api.service.abandon.AbandonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 유기동물 게시판 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/abandon")
public class AbandonController {

    private final AbandonService abandonService;
    private final AbandonQueryService abandonQueryService;

    /**
     * 유기동물 게시글 등록 API
     *
     * @param request 유기동물 게시글 정보
     * @param file    첨부파일
     * @return 등록된 PK 값
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createAbandon(@Valid @RequestPart(name = "request") CreateAbandonRequest request,
                                           @RequestPart(required = false, name = "file") MultipartFile file) {
        log.debug("AbandonController#createAbandon called.");
        log.debug("CreateAbandonRequest={}", request);
        log.debug("MultipartFile={}", file);

        // TODO: 2024-02-29 Security 에서 로그인한 회원 탐색 필요
        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = abandonService.createAbandon(request.toDto(), nickname, file);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 유기동물 게시글 목록 조회 API
     *
     * @return 검색 조건에 해당하는 유기동물 게시글 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<List<AbandonResponse>>> getAbandons() {
        // TODO: 2024-03-04 검색조건 추가되어야함.
        log.debug("AbandonController#getAbandons called.");

        PageResponse<List<AbandonResponse>> pageResponses = abandonQueryService.getAbandons();
        log.debug("pageResponses={}", pageResponses);

        return ApiResponse.ok(pageResponses);
    }

    /**
     * 유기동물 게시글 상세 조회 API
     *
     * @param abandonId 유기동물 게시글 PK
     * @return PK에 해당하는 유기동물 게시글 정보
     */
    @GetMapping("/{abandonId}")
    public ApiResponse<AbandonDetailResponse> getAbandon(@PathVariable(name = "abandonId") Long abandonId) {
        log.debug("AbandonController#getAbandon called.");
        log.debug("abandonId={}", abandonId);

        AbandonDetailResponse response = abandonQueryService.getAbandon(abandonId);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
