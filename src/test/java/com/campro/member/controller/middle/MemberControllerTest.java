package com.campro.member.controller.middle;

import com.campro.common.controller.ExceptionControllerAdvice;
import com.campro.common.controller.ResponseCode;
import com.campro.common.controller.response.ApiResponse;
import com.campro.common.exception.ErrorCode;
import com.campro.member.application.MemberService;
import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.presentation.controller.MemberController;
import com.campro.member.presentation.controller.response.MemberSignupResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@WithMockUser
@DisplayName("회원가입 컨트롤러 미들 테스트")
@WebMvcTest(MemberController.class)
@ContextConfiguration(classes = MemberController.class)
class MemberControllerTest {

    MockMvc mockMvc;

    @MockBean
    private MemberService memberService;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MemberController(memberService))
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();
    }

    @Nested
    @DisplayName("회원가입 성공 테스트")
    class MemberSignupSuccessTest {

        @Test
        @DisplayName("정상 데이터로 회원가입 요청 시 요청 처리 후 정상 응답을 반환한다.")
        void 정상적인_회원가입_요청_테스트() throws Exception {
            // given
            MemberSignupRequest memberSignupRequest = new MemberSignupRequest("testNickname", "test@test.com", "test123");
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
                            objectMapper.writeValueAsString(
                                    ApiResponse.from(
                                            ResponseCode.SIGNUP_SUCCESS.getCode(),
                                            ResponseCode.SIGNUP_SUCCESS.getMessage(),
                                            memberSignupResponse
                                    )
                            ))
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
            @DisplayName("비정상 이메일(' ')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 공백_이메일_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("testNickname", " ", "test123");
                Map<String, String> response = new HashMap<>();
                response.put("email", "이메일 형식에 맞지않는 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }

            @Test
            @DisplayName("비정상 이메일('')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 비어있는_이메일_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("testNickname", "", "test123");
                Map<String, String> response = new HashMap<>();
                response.put("email", "이메일 형식에 맞지않는 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }

            @Test
            @DisplayName("비정상 이메일(형식)로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 형식에_맞지_않는_이메일_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("testNickname", "test123", "test123");
                Map<String, String> response = new HashMap<>();
                response.put("email", "이메일 형식에 맞지않는 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }
        }

        @Nested
        @DisplayName("비정상 닉네임 회원가입 실패 테스트")
        class MemberWrongNicknameSignupFailTest {

            @Test
            @DisplayName("비정상 닉네임(' ')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 공백_닉네임_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest(" ", "test@test.com", "test123");
                Map<String, String> response = new HashMap<>();
                response.put("nickname", "닉네임은 필수 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }

            @Test
            @DisplayName("비정상 닉네임('')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 비어있는_닉네임_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("", "test@test.com", "test123");
                Map<String, String> response = new HashMap<>();
                response.put("nickname", "닉네임은 필수 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }

            @Test
            @DisplayName("비정상 닉네임(null)로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void null_닉네임_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest(null, "test@test.com", "test123");
                Map<String, String> response = new HashMap<>();
                response.put("nickname", "닉네임은 필수 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }
        }

        @Nested
        @DisplayName("비정상 패스워드 회원가입 실패 테스트")
        class MemberWrongPasswordSignupFailTest {

            @Test
            @DisplayName("비정상 패스워드(' ')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 공백_패스워드_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("testNickname", "test@test.com", " ");
                Map<String, String> response = new HashMap<>();
                response.put("password", "비밀번호는 필수 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }

            @Test
            @DisplayName("비정상 패스워드('')로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void 비어있는_패스워드_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("testNickname", "test@test.com", "");
                Map<String, String> response = new HashMap<>();
                response.put("password", "비밀번호는 필수 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }

            @Test
            @DisplayName("비정상 패스워드(null)로 회원가입 요청 시 요청 처리 후 비정상 응답을 반환한다.")
            void null_패스워드_회원가입_요청_테스트() throws Exception {
                // given
                MemberSignupRequest memberSignupRequest = new MemberSignupRequest("testNickname", "test@test.com", null);
                Map<String, String> response = new HashMap<>();
                response.put("password", "비밀번호는 필수 입력값입니다.");

                // when & then
                mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringify(memberSignupRequest))
                                .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(
                                objectMapper.writeValueAsString(
                                        ApiResponse.from(
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                                                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(),
                                                response
                                        )
                                ))
                        );
            }
        }
    }

    private String stringify(Object object) throws Exception {
        return new ObjectMapper().writeValueAsString(object);
    }
}

