package org.cybersoft.bookingticketcinemabe.payload.request;

import java.math.BigDecimal;
import java.util.List;

public record BranchUpdateRequest(String name,
                                  String logo,
                                  String avatar,
                                  String link,
                                  String address,
                                  Integer districtId,
                                  Integer cinemaId,
                                  List<Integer> movieIds,
                                  BigDecimal distance,
                                  BigDecimal lat,
                                  BigDecimal lon,
                                  BigDecimal rating,
                                  List<Integer> hallIds) {
}