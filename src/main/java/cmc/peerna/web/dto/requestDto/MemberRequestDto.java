package cmc.peerna.web.dto.requestDto;

import lombok.Getter;

public class MemberRequestDto {

    @Getter
    public static class OAuthDTO{
        private String accessToken;
    }
}