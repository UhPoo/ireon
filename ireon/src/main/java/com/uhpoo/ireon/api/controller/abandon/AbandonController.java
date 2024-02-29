package com.uhpoo.ireon.api.controller.abandon;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonRequest;
import com.uhpoo.ireon.api.service.abandon.AbandonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 유기동물 게시글 등록 API
     *
     * @param request       유기동물 게시글 정보
     * @param file 첨부파일
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
}
