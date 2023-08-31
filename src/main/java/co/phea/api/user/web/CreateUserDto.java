package co.phea.api.user.web;

import co.phea.api.user.UserRole;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateUserDto(
                            String name,
                            String gender,
                            String email,
                            String phoneNumber,

                            List<Long> roleIds) {
}
