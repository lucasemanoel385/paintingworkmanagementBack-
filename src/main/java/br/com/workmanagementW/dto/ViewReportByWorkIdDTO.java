package br.com.workmanagementW.dto;

import java.math.BigDecimal;

public record ViewReportByWorkIdDTO(
        BigDecimal expense,
        BigDecimal valueGross,
        BigDecimal payment,
        BigDecimal extraWork,
        BigDecimal valueLiquid) {

}
