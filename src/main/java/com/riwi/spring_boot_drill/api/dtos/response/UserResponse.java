package com.riwi.spring_boot_drill.api.dtos.response;

import com.riwi.spring_boot_drill.utils.enums.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String fullName;
    private RoleUser role;
    private String email;
}
