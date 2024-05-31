package com.uhpoo.ireon.api.service.abandon;

import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonDto;
import com.uhpoo.ireon.api.service.abandon.dto.EditAbandonDto;
import com.uhpoo.ireon.domain.abandon.Abandon;
import com.uhpoo.ireon.domain.abandon.AbandonAttachment;
import com.uhpoo.ireon.domain.abandon.repository.AbandonAttachmentRepository;
import com.uhpoo.ireon.domain.abandon.repository.AbandonRepository;
import com.uhpoo.ireon.domain.member.Member;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import com.uhpoo.ireon.global.file.S3Uploader;
import com.uhpoo.ireon.global.file.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * 유기동물 게시글 서비스
 *
 * @author 최영환
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AbandonService {

    private final AbandonRepository abandonRepository;
    private final MemberRepository memberRepository;
    private final AbandonAttachmentRepository abandonAttachmentRepository;
    private final S3Uploader s3Uploader;

    /**
     * 유기동물 게시글 등록
     *
     * @param dto      유기동물 게시글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @param file     등록할 첨부파일
     * @return 등록된 PK 값
     */
    public Long createAbandon(CreateAbandonDto dto, String nickname, MultipartFile file) throws IOException {
        log.debug("CreateAbandonDto={}", dto);
        log.debug("nickname={}", nickname);

        Member member = memberRepository.getMemberByNickname(nickname)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        Abandon abandon = abandonRepository.save(dto.toEntity(member));

        if (file != null && !file.isEmpty()) {
            String storeFileName = s3Uploader.uploadFiles(file, "abandon");
            UploadFile uploadFile = UploadFile.builder()
                    .storeFileName(storeFileName)
                    .uploadFileName(file.getOriginalFilename())
                    .build();

            AbandonAttachment attachment = AbandonAttachment.builder()
                    .abandon(abandon)
                    .uploadFile(uploadFile)
                    .build();
            abandonAttachmentRepository.save(attachment);
        }

        return abandon.getId();
    }

    /**
     * 유기동물 게시글 수정
     *
     * @param dto      유기동물 게시글 정보
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @param file     첨부파일
     * @return 수정된 PK 값
     */
    public Long editAbandon(EditAbandonDto dto, String nickname, MultipartFile file) {
        return null;
    }

    /**
     * 유기동물 게시글 삭제
     *
     * @param abandonId 삭제할 유기동물 게시글 PK
     * @param nickname  현재 로그인 중인 회원 닉네임
     * @return 삭제된 PK 값
     */
    public Long deleteAbandon(Long abandonId, String nickname) {
        return null;
    }
}
