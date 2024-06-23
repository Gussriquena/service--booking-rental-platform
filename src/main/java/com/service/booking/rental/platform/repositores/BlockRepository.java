package com.service.booking.rental.platform.repositores;

import java.time.LocalDate;

public interface BlockRepository {

    boolean hasBlocksByPropertyWithinDate(Long idProperty, LocalDate startDate, LocalDate endDate);

}
