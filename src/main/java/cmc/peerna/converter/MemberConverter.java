package cmc.peerna.converter;

import cmc.peerna.domain.Member;
import cmc.peerna.domain.PeerFeedback;
import cmc.peerna.domain.enums.SocialType;
import cmc.peerna.service.MemberService;
import cmc.peerna.web.dto.requestDto.MemberRequestDto;
import cmc.peerna.web.dto.responseDto.MemberResponseDto;
import cmc.peerna.web.dto.responseDto.RootResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MemberConverter {
    private static MemberService memberService;
    public static Member toMember(String socialId, SocialType socialType) {
        return Member.builder()
                .socialId((socialId))
                .socialType(socialType)
                .build();
    }

    public static Member toMemberById(Long memberId) {
        return memberService.findById(memberId);
    }

    public static MemberResponseDto.MemberStatusDto toMemberStatusDto(Long memberId, String status) {
        return MemberResponseDto.MemberStatusDto.builder()
                .memberId(memberId)
                .status(status)
                .calledAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDto.MemberSimpleInfoDto toSimpleInfoDto(Member member) {
        return MemberResponseDto.MemberSimpleInfoDto.builder()
                .name(member.getName())
                .testType(member.getSelfTestType())
                .part(member.getPart())
                .job(member.getJob())
                .totalScore(member.getTotalScore())
                .oneLiner(member.getOneliner())
                .build();
    }

    public static RootResponseDto.AllFeedbackDto toFeedbackString(Page<PeerFeedback> feedbackList, Member member) {
        List<String> feedbackContentList = feedbackList.stream()
                .map(feedback -> feedback.getContents())
                .collect(Collectors.toList());

        return RootResponseDto.AllFeedbackDto.builder()
                .feedbackList(feedbackContentList)
                .isFirst(feedbackList.isFirst())
                .isLast(feedbackList.isLast())
                .totalPage(feedbackList.getTotalPages())
                .totalElements(feedbackList.getTotalElements())
                .currentPageElements(feedbackList.getNumberOfElements())
                .build();
    }

    public static MemberResponseDto.memberBasicInfoDto toMemberBasicInfoDto(Member member) {
        return MemberResponseDto.memberBasicInfoDto.builder()
                .name(member.getName())
                .job(member.getJob())
                .part(member.getPart())
                .uuid(member.getUuid())
                .oneLiner(member.getOneliner())
                .build();
    }

    public static MemberRequestDto.profileUpdateDto toProfileUpdateDto(Member member) {
        return MemberRequestDto.profileUpdateDto.builder()
                .job(member.getJob())
                .part(member.getPart())
                .oneLiner(member.getOneliner())
                .build();
    }

}
