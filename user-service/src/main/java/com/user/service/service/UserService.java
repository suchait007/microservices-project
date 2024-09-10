package com.user.service.service;


import com.user.service.dto.InvoiceDTO;
import com.user.service.dto.UserInfo;
import com.user.service.entities.UserE;
import com.user.service.exception.UserException;
import com.user.service.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final InvoiceService invoiceService;
    private final UserRepository userRepository;

    public List<UserInfo> getUsers(List<String> ids) {


        if(CollectionUtils.isEmpty(ids)) {
            throw new UserException("Bad request", List.of("userIds are missing in request."), HttpStatus.BAD_REQUEST);
        }

        log.info("Calling database with ids : {} ", ids);
        List<UserE> userEList = userRepository.findAllById(ids);

        log.info("Sending response back.");

        return userEList.stream()
                .map(this::getUserInfo)
                .toList();
    }

    private UserInfo getUserInfo(UserE user) {

        log.info("calling invoice-service for user-id : {} ", user.getId());
        List<InvoiceDTO> invoiceDTOS = invoiceService.callInvoiceServiceAndGetInvoiceDTOList(user.getId());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setAge(user.getAge());
        userInfo.setGender(user.getGender());
        userInfo.setCreatedTime(user.getCreatedTime());
        userInfo.setUpdatedTime(user.getUpdatedTime());
        userInfo.setInvoiceDTOList(invoiceDTOS);

        return userInfo;
    }

}
