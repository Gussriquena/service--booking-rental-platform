package com.service.booking.rental.platform.transportlayers.mappers;

import com.service.booking.rental.platform.entities.security.AccountCredentials;
import com.service.booking.rental.platform.transportlayers.http.request.AccountCredentialsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountCredentialsMapper {

    AccountCredentialsMapper INSTANCE = Mappers.getMapper(AccountCredentialsMapper.class);

    AccountCredentials map(AccountCredentialsRequest request);

}
