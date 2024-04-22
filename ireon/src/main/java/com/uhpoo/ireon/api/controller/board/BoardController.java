package com.uhpoo.ireon.api.controller.board;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.board.request.CreateBoardRequest;
import com.uhpoo.ireon.api.service.board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 자유게시판 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 자유게시판 게시글 등록 API
     *
     * @param request 등록할 게시글 정보
     * @param file    첨부파일
     * @return 등록된 게시글 PK
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createBoard(@Valid @RequestPart(name = "request") CreateBoardRequest request,
                                         @RequestPart(required = false, name = "file") MultipartFile file) {
        log.debug("BoardController#createBoard called.");
        log.debug("CreateBoardRequest={}", request);
        log.debug("MultipartFile={}", file);

        String nickname = "nickname";
        log.debug("nickname={}", nickname);

        Long saveId = boardService.createBoard(request.toDto(), nickname, file);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }
}
