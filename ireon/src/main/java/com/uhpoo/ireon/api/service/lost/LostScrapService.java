package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.service.lost.dto.AddLostScrapDto;
import com.uhpoo.ireon.domain.lost.repository.LostScrapRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 실종동물 게시글 스크랩 서비스
 *
 * @author CYJ
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LostScrapService {
    private final LostScrapRepository lostScrapRepository;

    /**
     * 실종동물 스크랩 추가
     *
     * @param dto       실종동물 게시글 정보
     * @return 등록 된 PK 값
     */
    public Long scrap(AddLostScrapDto dto){
        return null;
    }
}
