package com.wrh.lombokuse;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: wuruohong
 * @Description:
 * @Date: Created in 2020-05-27
 * @Modified By:
 */
@Data
@Builder
public class GameDayDataDateFlag {

    private String dsEnd;
    private boolean isDsEnd;

    private String yesterday;
    private boolean isYesterday;

    private String theDayBeforeYesterday;
    private boolean isTheDayBeforeYesterday;

    private String lastWeekDay;
    private boolean isLastWeekDay;

    private String yesterdayLastWeekDay;
    private boolean isYesterdayLastWeekDay;

    private boolean offlineFlag;
}
