package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostRequest;
import com.uhpoo.ireon.api.service.lost.LostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 실종동물 게시판 API 컨트롤러
 *
 * @author yekk1
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lost")
public class LostController {

    private final LostService lostService;
    /**
     * 실종동물 게시글 등록 API
     *
     * @param request   실종동물 게시글 정보
     * @param file      첨부파일
     * @return 등록된 PK 값
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createLost(@Valid @RequestPart(name = "request") CreateLostRequest request, @RequestPart(required = false, name = "file") MultipartFile file) {
        log.debug("LostController#createLost called.");
        log.debug("CreateLostRequest={}", request);
        log.debug("MultipartFile={}", file);

        // 임시 닉네임
        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = lostService.createLost(request.toDto(), nickname, file);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }
}
