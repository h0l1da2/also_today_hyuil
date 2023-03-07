package today.also.hyuil.domain.member;

import lombok.Getter;
import today.also.hyuil.domain.dto.MemberJoinDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Address {

    @Id @GeneratedValue
    private Long id;
    private String address;
    private String autoRoadAddress;
    private String zonecode;
    private String detail;

    protected Address() {}

    public Address(MemberJoinDto memberJoinDto) {
        this.address = memberJoinDto.getAddress();
        this.autoRoadAddress = memberJoinDto.getAutoRoadAddress();
        this.zonecode = memberJoinDto.getZonecode();
        this.detail = memberJoinDto.getDetail();
    }
}