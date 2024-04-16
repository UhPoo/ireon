package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.board.BoardController;
import com.uhpoo.ireon.api.controller.board.request.CreateBoardRequest;
import com.uhpoo.ireon.api.controller.board.request.EditBoardRequest;
import com.uhpoo.ireon.api.controller.board.response.BoardDetailResponse;
import com.uhpoo.ireon.api.controller.board.response.BoardResponse;
import com.uhpoo.ireon.api.service.board.BoardQueryService;
import com.uhpoo.ireon.api.service.board.BoardService;
import com.uhpoo.ireon.api.service.board.dto.CreateBoardDto;
import com.uhpoo.ireon.api.service.board.dto.EditBoardDto;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardControllerDocsTest.class)
public class BoardControllerDocsTest extends RestDocsSupport {

    private final BoardService boardService = mock(BoardService.class);
    private final BoardQueryService boardQueryService = mock(BoardQueryService.class);

    @Override
    protected Object initController() {
        return new BoardController(boardService, boardQueryService);
    }

    @DisplayName("자유게시판 게시글 등록 API")
    @Test
    @WithMockUser
    void createBoard() throws Exception {
        CreateBoardRequest request = CreateBoardRequest.builder()
                .title("제목")
                .content("내용")
                .build();

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        String jsonRequest = objectMapper.writeValueAsString(request);
        MockMultipartFile jsonRequestPart = new MockMultipartFile("request", "request.json",
                APPLICATION_JSON_VALUE, jsonRequest.getBytes(UTF_8));

        given(boardService.createBoard(any(CreateBoardDto.class), anyString(), any(MultipartFile.class)))
                .willReturn(1L);

        mockMvc.perform(
                        multipart("/board")
                                .file(file)
                                .file(jsonRequestPart)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-board",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParts(
                                partWithName("file").description("첨부파일"),
                                partWithName("request").description("자유게시판 게시글 정보")
                        ),
                        requestPartFields("request",
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("게시글 내용")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("등록된 자유게시판 게시글 PK 값")
                        )
                ));
    }

    @DisplayName("게시글 전체 조회 API")
    @Test
    @WithMockUser
    void getAbandons() throws Exception {

        BoardResponse item1 = BoardResponse.builder()
                .boardId(3L)
                .title("제목1")
                .author("작성자1")
                .clipped(true)
                .createdDate("2024-03-05")
                .build();

        BoardResponse item2 = BoardResponse.builder()
                .boardId(1L)
                .title("제목2")
                .author("작성자2")
                .clipped(false)
                .createdDate("2024-03-04")
                .build();

        List<BoardResponse> items = List.of(item1, item2);

        PageResponse<List<BoardResponse>> response = PageResponse.of(false, items);

        given(boardQueryService.getBoards(anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/board")
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-boards",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN)
                                        .description("다음 페이지 존재 여부"),
                                fieldWithPath("data.items").type(JsonFieldType.ARRAY)
                                        .description("게시글 목록"),
                                fieldWithPath("data.items[].boardId").type(JsonFieldType.NUMBER)
                                        .description("자유게시판 게시글 PK"),
                                fieldWithPath("data.items[].title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                fieldWithPath("data.items[].author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.items[].clipped").type(JsonFieldType.BOOLEAN)
                                        .description("스크랩 여부"),
                                fieldWithPath("data.items[].createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));

    }

    @DisplayName("자유게시판 상세 조회 API")
    @Test
    @WithMockUser
    void getBoard() throws Exception {

        BoardDetailResponse response = BoardDetailResponse.builder()
                .boardId(1L)
                .title("제목1")
                .author("작성자1")
                .content("내용1")
                .clipped(true)
                .createdDate("2024-03-05")
                .build();


        given(boardQueryService.getBoard(anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/board/{boardId}", 1L)
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-board",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("boardId").description("자유게시판 게시글 PK")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("자유게시판 상세 조회 결과"),
                                fieldWithPath("data.boardId").type(JsonFieldType.NUMBER)
                                        .description("자유게시판 게시글 PK"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                fieldWithPath("data.author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("글 내용"),
                                fieldWithPath("data.clipped").type(JsonFieldType.BOOLEAN)
                                        .description("스크랩 여부"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));

    }

    @DisplayName("자유게시판 수정 API")
    @Test
    @WithMockUser
    void editBoard() throws Exception {

        Long boardId = 1L;

        EditBoardRequest request = EditBoardRequest.builder()
                .title("수정할 제목")
                .content("이에용")
                .build();

        given(boardService.editBoard(any(EditBoardDto.class), anyString(), any(MultipartFile.class)))
                .willReturn(boardId);

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        String jsonRequest = objectMapper.writeValueAsString(request);
        MockMultipartFile jsonRequestPart = new MockMultipartFile("request", "request.json",
                APPLICATION_JSON_VALUE, jsonRequest.getBytes(UTF_8));

        // multipart 는 기본적으로 POST 요청을 위한 처리로만 사용되므로 다음과 같이 Override 해서 요청을 만들어줘야함
        MockMultipartHttpServletRequestBuilder builder =
                RestDocumentationRequestBuilders.
                        multipart("/board/{boardId}", boardId);

        builder.with(new RequestPostProcessor() {
            @Override
            public @NotNull MockHttpServletRequest postProcessRequest(@NotNull MockHttpServletRequest request) {
                request.setMethod("PATCH");
                return request;
            }
        });

        mockMvc.perform(
                        builder
                                .file(file)
                                .file(jsonRequestPart)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("edit-board",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("boardId").description("자유게시판 게시글 PK")
                        ),
                        requestParts(
                                partWithName("file").description("첨부파일"),
                                partWithName("request").description("자유게시판 게시글 정보")
                        ),
                        requestPartFields("request",
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("게시글 내용")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("수정된 자유게시판 게시글 PK 값")
                        )
                ));
    }

    @DisplayName("자유게시판 게시글 삭제 API")
    @Test
    @WithMockUser
    void deleteAbandon() throws Exception {

        Long boardId = 1L;

        given(boardService.deleteBoard(anyLong(), anyString()))
                .willReturn(boardId);

        mockMvc.perform(
                        delete("/board/{boardId}", boardId)
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-board",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("boardId").description("자유게시판 게시글 PK")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("삭제된 자유게시판 게시글 PK 값")
                        )
                ));
    }
}
