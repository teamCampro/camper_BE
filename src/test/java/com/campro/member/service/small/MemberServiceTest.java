package com.campro.member.service.small;

import com.campro.auth.application.AuthTokenService;
import com.campro.auth.application.JwtService;
import com.campro.common.exception.ApiException;
import com.campro.encrypt.application.service.EncryptService;
import com.campro.member.application.MemberService;
import com.campro.member.application.MemberServiceImpl;
import com.campro.member.application.request.MemberSignupRequest;
import com.campro.member.domain.Member;
import com.campro.member.domain.Role;
import com.campro.member.infrastructure.MemberRepository;
import com.campro.mock.EncryptFakeService;
import com.campro.mock.FakeTokenService;
import com.campro.mock.MemberFakeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("회원가입 서비스 스몰 테스트")
public class MemberServiceTest {

    private MemberService memberService;
    private MemberRepository memberFakeRepository;

    @BeforeEach
    void setUp() {
        EncryptService encryptFakeService = new EncryptFakeService();
        AuthTokenService tokenService = new FakeTokenService();
        memberFakeRepository = new MemberFakeRepository();
        memberService = new MemberServiceImpl(memberFakeRepository, tokenService, encryptFakeService);
        memberFakeRepository.save(new Member(
                1L,
                "nickname",
                "email",
                "password",
                "profileImage",
                Role.USER)
        );
    }

    @Nested
    @DisplayName("회원가입 성공 테스트")
    class MemberSignupSuccessTest {

        @Test
        @DisplayName("회원가입이 정상적으로 진행되면 저장소에 회원 정보가 저장된다.")
        void 회원가입_성공_테스트() {
            // given
            MemberSignupRequest request = new MemberSignupRequest(
                    "testNickname", "testEmail", "testPassword"
            );

            // when
            memberService.signup(request);

            // then
            Assertions.assertThat(memberFakeRepository.findByEmail("testEmail")).isNotEmpty();
        }
    }

    @Nested
    @DisplayName("회원가입 실패 테스트")
    class MemberSignupFailTest {

        @Test
        @DisplayName("중복된 이메일로 회원가입을 시도하면 예외가 발생한다.")
        void 중복된_이메일_회원가입_실패_테스트() {
            // given
            MemberSignupRequest request = new MemberSignupRequest(
                    "testNickname", "email", "testPassword"
            );

            // when & then
            Assertions.assertThatThrownBy(() -> memberService.signup(request))
                    .isInstanceOf(ApiException.class)
                    .hasMessage("이미 사용중인 이메일 입니다.");
        }

        @Test
        @DisplayName("중복된 닉넥임으로 회원가입을 시도하면 예외가 발생한다.")
        void 중복된_닉네임_회원가입_실패_테스트() {
            // given
            MemberSignupRequest request = new MemberSignupRequest(
                    "nickname", "testEmail", "testPassword"
            );

            // when & then
            Assertions.assertThatThrownBy(() -> memberService.signup(request))
                    .isInstanceOf(ApiException.class)
                    .hasMessage("이미 사용중인 닉네임 입니다.");
        }
    }
}
