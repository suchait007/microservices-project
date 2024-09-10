package com.user.service.controller;

import com.user.service.dto.UserInfo;
import com.user.service.exception.UserError;
import com.user.service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get Users Details", description = "Fetches user information in detail along with Invoices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success retrieved user information",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfo.class)) }),
            @ApiResponse(responseCode = "400", description = "bad request, some missing field",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserError.class)))
    })
    @GetMapping("/users")
    public List<UserInfo> getUsers(@RequestParam("ids") List<String> ids) {

        log.info("Received request for ids : {} ", ids);

        return userService.getUsers(ids);
    }

}
