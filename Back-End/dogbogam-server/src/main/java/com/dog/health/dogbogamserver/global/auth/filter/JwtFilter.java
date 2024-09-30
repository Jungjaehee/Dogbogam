package com.dog.health.dogbogamserver.global.auth.filter;

import com.dog.health.dogbogamserver.domain.member.domain.Member;
import com.dog.health.dogbogamserver.domain.member.application.service.MemberService;
import com.dog.health.dogbogamserver.global.auth.dto.MemberPrincipal;
import com.dog.health.dogbogamserver.global.auth.utils.JWTProvider;
import com.dog.health.dogbogamserver.global.web.exception.CustomException;
import com.dog.health.dogbogamserver.global.web.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean{

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JWTProvider jwtProvider;
    private final MemberService memberService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("[JwtFilter] : " + request.getRequestURL().toString());

        // 로그인, 회원가입은 필터를 적용하지 않음
        // 추가로 swagger도 필터를 적용하지 않음
        String requestURI = request.getRequestURI();
        logger.info("requestURI : " + requestURI);
        if (requestURI.equals("/members") || requestURI.equals("/members/login") || requestURI.equals("/member/check")
                || requestURI.startsWith("/swagger-ui") || requestURI.startsWith("/v3/api-docs")
                || requestURI.equals("/error") || requestURI.equals("/favicon.ico")) {
            filterChain.doFilter(request, servletResponse);
            return;
        }

        String jwt = resolveToken(request);

        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            Long memberId = jwtProvider.getMemberId(jwt);

            Member member = memberService.findByMemberId(memberId);

            UserDetails userDetails = MemberPrincipal.createMemberAuthority(member);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else{
            throw new CustomException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
        filterChain.doFilter(request, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
