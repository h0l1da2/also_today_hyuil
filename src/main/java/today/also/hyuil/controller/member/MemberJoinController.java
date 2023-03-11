package today.also.hyuil.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import today.also.hyuil.domain.dto.member.DoubleCheckDto;
import today.also.hyuil.domain.dto.member.MemberJoinDto;
import today.also.hyuil.domain.member.Address;
import today.also.hyuil.domain.member.Member;
import today.also.hyuil.domain.member.Role;
import today.also.hyuil.service.member.MailService;
import today.also.hyuil.service.member.MemberJoinService;


@Controller
@RequestMapping("/join")
public class MemberJoinController {

    private String code;
    private final MemberJoinService memberJoinService;
    private final MailService mailService;

    public MemberJoinController(MemberJoinService memberJoinService, MailService mailService) {
        this.memberJoinService = memberJoinService;
        this.mailService = mailService;
    }

    @ResponseBody
    @PostMapping("/idCheck")
    public String idDoubleCheck(@RequestBody DoubleCheckDto doubleCheckDto) {
        if (stringNullCheck(
                doubleCheckDto.getMemberId())) {
            return "확인 불가";
        }

        Member member = memberJoinService.idDoubleCheck(
                doubleCheckDto.getMemberId()
        );

        if (member != null) {
            return "중복";
        }
        return "가입 가능";
    }

    @ResponseBody
    @PostMapping("/nicknameCheck")
    public String nicknameCheck(@RequestBody DoubleCheckDto doubleCheckDto) {
        if (stringNullCheck(
                doubleCheckDto.getNickname())) {
            return "확인 불가";
        }

        Member member = memberJoinService.nicknameCheck(
                doubleCheckDto.getMemberId()
        );
        if (memberNullCheck(member)) {
            return "중복";
        }
        return "가입 가능";
    }

    @ResponseBody
    @PostMapping("/phoneCheck")
    public String phoneCheck(@RequestBody DoubleCheckDto doubleCheckDto) {
        if (stringNullCheck(
                doubleCheckDto.getPhone())) {
            return "확인 불가";
        }

        Member member = memberJoinService.phoneCheck(
                doubleCheckDto.getMemberId()
        );
        if (memberNullCheck(member)) {
            return "중복";
        }
        return "가입 가능";
    }

    @ResponseBody
    @PostMapping("/emailSend")
    public String emailCodeSend(@RequestBody DoubleCheckDto doubleCheckDto) {
        if (stringNullCheck(
                doubleCheckDto.getEmail())) {
            return "확인 불가";
        }

        code = mailService.joinCodeSend(doubleCheckDto.getEmail());
        return code;
    }

    @PostMapping("/complete")
    public String joinMember(MemberJoinDto memberJoinDto) {

        Member member = new Member(memberJoinDto,
                new Address(memberJoinDto), new Role(memberJoinDto));
        memberJoinService.joinMember(member);
        return "member/joinComplete";
    }

    private boolean stringNullCheck(String str) {
        if (str == null) {
            return true;
        }
        if (str.equals("")) {
            return true;
        }
        if (str.equals(" ")) {
            return true;
        }
        return false;
    }

    private boolean memberNullCheck(Member member) {
        if (member == null) {
            return true;
        }
        return false;
    }
}
