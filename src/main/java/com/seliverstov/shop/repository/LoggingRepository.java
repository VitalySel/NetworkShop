package com.seliverstov.shop.repository;

import com.seliverstov.shop.models.Logging;

import java.util.List;

public interface LoggingRepository <P extends Logging> {

    List getLogging();
}
