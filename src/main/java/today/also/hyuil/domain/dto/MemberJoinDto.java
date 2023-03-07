package today.also.hyuil.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import today.also.hyuil.domain.member.Name;
import today.also.hyuil.domain.member.Sns;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class MemberJoinDto {

    private String userId;
    private String password;
    private String name;
    private String nickname;
    @Email
    private String email;
    private String phone;
    private String address;
    private String autoRoadAddress;
    private String zonecode;
    private String detail;
    private Name roleName;
    private Sns sns;

}