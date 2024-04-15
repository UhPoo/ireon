package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostRequest;
import com.uhpoo.ireon.api.controller.lost.request.EditLostRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostDetailResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostResponse;
import com.uhpoo.ireon.api.service.lost.LostQueryService;
import com.uhpoo.ireon.api.service.lost.LostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private final LostQueryService lostQueryService;

    /**
     * 실종동물 게시글 등록 API
     *
     * @param request   실종동물 게시글 정보
     * @param file      첨부파일
     * @return 등록 된 PK 값
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

    /**
     * 실종동물 게시글 수정 API
     *
     * @param lostId    수정할 실종동굴 게시글 PK
     * @param request   실종동물 게시글 수정 정보
     * @param file      첨부파일
     * @return 수정 된 PK 값
     */
    @PatchMapping("/{lostId}")
    public ApiResponse<Long> editLost(@PathVariable(name = "lostId") Long lostId,
                                      @Valid @RequestPart(name = "request") EditLostRequest request,
                                      @RequestPart(required = false, name = "file") MultipartFile file) {
        log.debug("LostController#editLost called.");
        log.debug("lostId={}", lostId);
        log.debug("EditLostRequest={}", request);
        log.debug("MultipartFile={}", file);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long editId = lostService.editLost(request.toDto(), nickname, file);
        log.debug("editId={}", editId);

        return ApiResponse.ok(editId);
    }

    /**
     * 실종동물 게시글 삭제 API
     *
     * @param lostId 삭제할 실종동물 게시글 PK
     * @return 삭제 된 PK 값
     */
    @DeleteMapping("/{lostId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Long> deleteLost(@PathVariable(name = "lostId") Long lostId){
        log.debug("LostController#deleteLost called.");
        log.debug("lostId={}", lostId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long deleteId = lostService.deleteLost(lostId, nickname);
        log.debug("deleteId={}", deleteId);

        return ApiResponse.found(deleteId);
    }

    /**
     * 실종동물 게시글 목록 조회 API
     *
     * @return 검색 조건에 해당하는 실종동물 게시글 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<List<LostResponse>>> getLosts() {
        // TODO: 검색조건 추가 필요
        log.debug("LostController#getLosts called.");

        PageResponse<List<LostResponse>> pageResponses = lostQueryService.getLosts();
        log.debug("pageResponses={}", pageResponses);

        return ApiResponse.ok(pageResponses);
    }

    /**
     * 실종동물 상세 게시글 조회 API
     *
     * @param lostId 실종동물 게시글 PK
     *
     * @return PK에 해당하는 실종동물 게시글 정보
     */
    @GetMapping("/{lostId}")
    public ApiResponse<LostDetailResponse> getLost(@PathVariable(name = "lostId") Long lostId) {
        log.debug("LostController#getLost called.");
        log.debug("lostId={}", lostId);

        LostDetailResponse lostDetailResponse = lostQueryService.getLost(lostId);
        log.debug("lostDetailResponse={}", lostDetailResponse);

        return ApiResponse.ok(lostDetailResponse);
    }
}
