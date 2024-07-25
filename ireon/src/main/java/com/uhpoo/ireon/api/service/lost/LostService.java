package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.api.service.lost.dto.CreateLostDto;
import com.uhpoo.ireon.api.service.lost.dto.EditLostDto;
import com.uhpoo.ireon.domain.lost.Lost;
import com.uhpoo.ireon.domain.lost.LostAttachment;
import com.uhpoo.ireon.domain.lost.repository.LostAttachmentRepository;
import com.uhpoo.ireon.domain.lost.repository.LostRepository;
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
 * 실종동물 게시글 서비스
 *
 * @author CYJ
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LostService {

    private final LostRepository lostRepository;
    private final MemberRepository memberRepository;
    private final LostAttachmentRepository lostAttachmentRepository;
    private final S3Uploader s3Uploader;
    /**
     * 실종동물 게시글 등록
     *
     *  @param dto 실종동물 게시글 정보
     *  @param nickname 현재 로그인 중인 회원 닉네임
     *  @param file 첨부파일
     *  @return 등록된 PK 값
     */
    public Long createLost(CreateLostDto dto, String nickname, MultipartFile file) throws IOException {
        log.debug("CreateLostDto = {}", dto);
        log.debug("nickname = {}", nickname);

        Member member = memberRepository.getMemberByNickname(nickname)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        Lost lost = lostRepository.save(dto.toEntity(member));

        if (file != null && !file.isEmpty()) {
            String storeFileName = s3Uploader.uploadFiles(file, "lost");
            UploadFile uploadFile = UploadFile.builder()
                    .storeFileName(storeFileName)
                    .uploadFileName(file.getOriginalFilename())
                    .build();

            LostAttachment attachment = LostAttachment.builder()
                    .lost(lost)
                    .uploadFile(uploadFile)
                    .build();
            lostAttachmentRepository.save(attachment);
        }

        return lost.getId();
    }

    /**
     * 실종동물 게시글 수정
     *
     *  @param dto 실종동물 게시글 수정 정보
     *  @param nickname 현재 로그인 중인 회원 닉네임
     *  @param file 첨부파일
     *  @return 수정 된 PK 값
     */
    public Long editLost(EditLostDto dto, String nickname, MultipartFile file) {
        return null;
    }

    /**
     *  실종동물 게시물 삭제
     *
     * @param LostId 삭제할 실종동물 게시글 PK 값
     * @param nickname 현재 로그인 중인 회원 닉네임
     * @return 삭제 된 PK 값
     */
    public Long deleteLost(Long LostId, String nickname) { return null; }
}
