package com.uhpoo.ireon.api.controller.lost;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostReportRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostDetailReportResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostReportResponse;
import com.uhpoo.ireon.api.service.lost.LostReportQueryService;
import com.uhpoo.ireon.api.service.lost.LostReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 실종동물 게시글 신고 API 컨트롤러
 *
 * @author CYJ
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lost/report")
public class LostReportController {
    private final LostReportService lostReportService;
    private final LostReportQueryService lostReportQueryService;

    /**
     * 실종동물 게시글 신고 등록 API
     *
     * @param request 실종동물 게시글 신고 정보
     * @return 등록된 실종동물 게시글 신고 PK
     */
    @PostMapping("/{lostId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createLostReport(@Valid @RequestBody CreateLostReportRequest request) {
        log.debug("LostReportController#createLostReport called.");
        log.debug("CreateLostRequest={}", request);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = lostReportService.createLostReport(request.toDto(), nickname);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 실종동물 게시글 신고 삭제 API
     *
     * @param lostReportId 삭제할 실종동물 게시글 신고 PK
     * @return 삭제 된 PK 값
     */
    @DeleteMapping("/{lostReportId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Long> deleteLostReport(@PathVariable(name = "lostReportId") Long lostReportId){
        log.debug("LostReportController#deleteLostReport called.");
        log.debug("lostReportId={}", lostReportId);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long deleteId = lostReportService.deleteLostReport(lostReportId, nickname);
        log.debug("deleteId={}", deleteId);

        return ApiResponse.found(deleteId);
    }

    /**
     * 실종동물 게시글 신고 목록 조회 API
     *
     * @return 신고한 실종동물 게시글 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<List<LostReportResponse>>> getLostReports() {
        log.debug("LostReportController#getLostReports called.");

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        PageResponse<List<LostReportResponse>> response = lostReportQueryService.getLostReports(nickname);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 실종동물 게시글 신고 상세 조회 API
     *
     * @param lostReportId 실종동물 게시글 신고 PK
     *
     * @return PK에 해당하는 실종동물 신고 게시글 정보
     */
    @GetMapping("/{lostReportId}")
    public ApiResponse<LostDetailReportResponse> getLostReport(@PathVariable(name = "lostReportId") Long lostReportId) {
        log.debug("LostReportController#getLostReport called.");
        log.debug("lostRerportId={}", lostReportId);

        LostDetailReportResponse lostDetailReportResponse = lostReportQueryService.getLostReport(lostReportId);
        log.debug("lostDetailReportResponse={}", lostDetailReportResponse);

        return ApiResponse.ok(lostDetailReportResponse);
    }
}
