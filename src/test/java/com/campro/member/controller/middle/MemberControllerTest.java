package com.campro.member.controller.middle;

import com.campro.common.config.WebSecurityConfig;
import com.campro.member.application.MemberService;
import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.presentation.controller.MemberController;
import com.campro.member.presentation.controller.response.MemberSignupResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@Import(WebSecurityConfig.class)
@WebMvcTest(MemberController.class)
@DisplayName("회원가입 테스트")
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MemberService memberService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Nested
    @DisplayName("회원가입 성공 테스트")
    class MemberSignupSuccessTest {

        @Test
        @DisplayName("정상 데이터로 회원가입 요청 시 요청 처리 후 정상 응답을 반환한다.")
        void 정상적인_회원가입_요청_테스트() throws Exception {
            // given
            MemberSignupRequest memberSignupRequest = new MemberSignupRequest("test123@test.com", "testNickname", "test123");
            MemberSignupResponse memberSignupResponse = new MemberSignupResponse("testNickname");
            BDDMockito.given(memberService.signup(memberSignupRequest))
                    .willReturn(memberSignupResponse);

            // when & then
            mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                            .with(SecurityMockMvcRequestPostProcessors.csrf())
                            .content(stringify(memberSignupRequest))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(
                            objectMapper.writeValueAsString(memberSignupResponse))
                    );

            BDDMockito.then(memberService).should().signup(memberSignupRequest);
        }
    }

    @Nested
    @DisplayName("회원가입 실패 테스트")
    class MemberSignupFailTest {

        @Nested
        @DisplayName("비정상 이메일 회원가입 실패 테스트")
        class MemberWrongEmailSignupFailTest {

            @Test
            @DisplayName("비정상 이메일(Null)로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void null_이메일_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest(null, "testNickname", "test123");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
            }

            @Test
            @DisplayName("비정상 이메일(' ')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 공백_이메일_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest(" ", "testNickname", "test123");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
            }

            @Test
            @DisplayName("비정상 이메일('')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 비어있는_이메일_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("", "testNickname", "test123");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
            }

            @Test
            @DisplayName("비정상 이메일(형식)로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 형식에_맞지_않는_이메일_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("test123", "testNickname", "test123");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
            }
        }
    }

    private String stringify(Object object) throws Exception {
        return new ObjectMapper().writeValueAsString(object);
    }
}
