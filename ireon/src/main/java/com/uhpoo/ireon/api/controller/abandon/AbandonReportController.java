package com.uhpoo.ireon.api.controller.abandon;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonReportDetailResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonReportResponse;
import com.uhpoo.ireon.api.service.abandon.AbandonReportQueryService;
import com.uhpoo.ireon.api.service.abandon.AbandonReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유기동물 게시글 신고 API 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/abandon/report")
public class AbandonReportController {

    private final AbandonReportService abandonReportService;
    private final AbandonReportQueryService abandonReportQueryService;

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

    /**
     * 유기동물 게시글 신고 목록 조회 API
     *
     * @return 유기동물 게시글 신고 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<List<AbandonReportResponse>>> getAbandonReports() {

        log.debug("AbandonReportController#getAbandonReports called.");

        PageResponse<List<AbandonReportResponse>> response = abandonReportQueryService.getAbandonReports();
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 유기동물 게시글 신고 상세 조회 API
     *
     * @param abandonReportId 유기동물 게시글 신고 PK
     * @return 유기동물 게시글 신고 상세 정보
     */
    @GetMapping("/{abandonReportId}")
    public ApiResponse<AbandonReportDetailResponse> getAbandonReport(
            @PathVariable(name = "abandonReportId") Long abandonReportId) {
        log.debug("AbandonReportController#getAbandonReport called.");
        log.debug("abandonReportId={}", abandonReportId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        AbandonReportDetailResponse response = abandonReportQueryService.getAbandonReport(abandonReportId, nickname);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 유기동물 게시글 신고 삭제 API
     *
     * @param abandonReportId 유기동물 게시글 신고 PK
     * @return 삭제된 신고 PK
     */
    @DeleteMapping("/{abandonReportId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Long> deleteAbandonReport(@PathVariable(name = "abandonReportId") Long abandonReportId) {
        log.debug("AbandonReportController#deleteAbandonReport called.");
        log.debug("abandonReportId={}", abandonReportId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long deleteId = abandonReportService.deleteAbandonReport(abandonReportId, nickname);
        log.debug("deleteId={}", deleteId);

        return ApiResponse.found(deleteId);
    }
}
