package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.service.lost.dto.CreateLostReportDto;
import com.uhpoo.ireon.domain.lost.repository.LostReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 실종동물 게시글 신고 서비스
 *
 * @author CYJ
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LostReportService {
    private final LostReportRepository lostReportRepository;

    /**
     * 실종동물 게시글 신고 등록
     *
     * @param dto 실종동물 게시글 신고 정보 Dto
     * @param nickname  현재 로그인 중인 회원의 닉네임
     * @return 등록된 신고 PK
     */
    public Long createLostReport(CreateLostReportDto dto, String nickname) {
        return null;
    }

    /**
     * 실종동물 게시글 신고 삭제
     *
     * @param lostReportId 실종동물 게시글 신고 PK
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 삭제된 신고 PK 값
     */
    public Long deleteLostReport(Long lostReportId, String nickname) {
        return null;
    }
}
