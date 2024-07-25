package com.uhpoo.ireon.api.service.lost;

import com.uhpoo.ireon.IntegrationTestSupport;
import com.uhpoo.ireon.api.service.lost.dto.CreateLostDto;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.lost.LostStatus;
import com.uhpoo.ireon.domain.lost.repository.LostAttachmentRepository;
import com.uhpoo.ireon.domain.lost.repository.LostRepository;
import com.uhpoo.ireon.domain.member.Member;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 실종동물 게시글 CUD 서비스 테스트
 *
 * @author CYJ
 */
@Slf4j
public class LostServiceTest extends IntegrationTestSupport {

    @Autowired
    private LostService lostService;
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("사용자는 실종동물 게시판에 첨부파일 없이 글을 등록할 수 있다.")
    @Test
    void createLostWithoutAttachment() throws IOException {
        // given
        createMember();

        CreateLostDto dto = getCreateLostDto();

        // when
        Long saveId = lostService.createLost(dto, "nickname", null);

        // then
        assertThat(saveId).isNotNull();
    }

    @DisplayName("사용자는 실종동물 게시판에 첨부파일과 함께 글을 등록할 수 있다.")
    @Test
    void createLostWithAttachment() throws IOException {
        // given
        createMember();

        CreateLostDto dto = getCreateLostDto();

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        // when
        Long saveId = lostService.createLost(dto, "nickname", file);

        // then
        assertThat(saveId).isNotNull();
    }

    private void createMember() {
        Member member = Member.builder()
                .email("test@gmail.com")
                .nickname("nickname")
                .encryptedPwd("test1234")
                .build();
        memberRepository.save(member);
    }

    private CreateLostDto getCreateLostDto() {

        return CreateLostDto.builder()
                .title("제목")
                .content("글내용")
                .animalType(AnimalType.DOG.toString())
                .animalDetail("말티즈")
                .animalGender(Gender.FEMALE.toString())
                .animalAge(2)
                .deSexing(DeSexing.UNDONE.toString())
                .lostStatus(LostStatus.LOST.toString())
                .zipcode("11111")
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .latitude(BigDecimal.valueOf(37.576987703009536))
                .longitude(BigDecimal.valueOf(126.98023424093205))
                .phoneNumber("010-1234-5678")
                .build();
    }
}
