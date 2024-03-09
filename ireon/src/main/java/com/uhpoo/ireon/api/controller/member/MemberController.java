package com.uhpoo.ireon.api.controller.member;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.member.request.MemberSignUpRequest;
import com.uhpoo.ireon.api.controller.member.response.MemberSignUpResponse;
import com.uhpoo.ireon.api.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberSignUpResponse> signup(@Valid @RequestBody MemberSignUpRequest request) {
        log.debug("MemberController#signup called.");
        log.debug("MemberSignUpRequest={}", request);

        MemberSignUpResponse response = memberService.signup(request.toDto());
        log.debug("MemberSignUpResponse={}",response);

        return ApiResponse.created(response);
    }
}
