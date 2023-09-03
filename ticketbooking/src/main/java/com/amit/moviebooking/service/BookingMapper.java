package com.amit.moviebooking.service;

import com.amit.moviebooking.dto.BookingRequest;
import com.amit.moviebooking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore id during mapping
    @Mapping(target = "status", ignore = true) // Set default status
    Booking bookingRequestToBooking(BookingRequest bookingRequest);
}

