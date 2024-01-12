package com.imeira.credit.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum IndicatorTypeEnum {

    PF(11),
    PJ(14),
    EU(8),
    AP(10);

    @Getter
    private int indicatorCode;

    public static IndicatorTypeEnum fromIndicatorCode(final int indicatorCode) {
        for (IndicatorTypeEnum item : IndicatorTypeEnum.values()) {
            if (item.indicatorCode == indicatorCode) {
                return item;
            }
        }
        return null;
    }

}
