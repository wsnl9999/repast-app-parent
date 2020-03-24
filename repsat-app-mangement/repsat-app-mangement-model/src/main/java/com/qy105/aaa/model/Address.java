package com.qy105.aaa.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Table(name = "ums_member_receive_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 20)
    @NotNull
    private Long id;
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "default_status")
    private Integer defaultStatus;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "region")
    private String region;
    @Column(name = "detail_address")
    private String detailAddress;


}