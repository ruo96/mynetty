package com.wrh.enums;

import com.wrh.enums.role.TourMarkEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:42 2019/5/21 0021
 * @Modified By:
 */
@Slf4j
public class TestEnum {
    public static void main(String[] args) {
        System.out.println(FootEnum.APPLE.name());

        FootEnum fn = FootEnum.valueOf(FootEnum.APPLE.name());
        System.out.println("fn name is : " + fn.name());

        String companyId = "10020";
        companyId = StringUtils.leftPad(companyId, 4, '0');
        System.out.println(companyId);
        companyId = StringUtils.substring(companyId, -4);
        System.out.println(companyId);

    }

    @Test
    public void test1(){

        FieldEnum fieldEnum = FieldEnum.getByKey("VARIETY_CODE");
        log.info("===> key: {}  val: {}",fieldEnum.key(),fieldEnum.val());

        FieldEnum fieldEnum1 = FieldEnum.getByVal("品种编码");
        log.info("===> key: {}  val: {}",fieldEnum1.key(),fieldEnum1.val());

        FieldEnum fieldEnum2 = FieldEnum.getByVal("投入品编码");
        log.info("===> key: {}  val: {}",fieldEnum2.key(),fieldEnum2.val());

    }

    @Test
    public void Test() {
        TourMarkEnum tourMarkEnum = TourMarkEnum.TOUR_MARK;
        String enumStr = "1";
        TourMarkEnum tourMarkEnum1 = TourMarkEnum.getByCode(enumStr);
        System.out.println(tourMarkEnum);
        System.out.println(tourMarkEnum.getClass() );
        System.out.println(tourMarkEnum1);
        System.out.println(tourMarkEnum1.getCode());
        System.out.println(tourMarkEnum1.getDesc());
        switch (tourMarkEnum1) {
            case TOUR_MARK:
                System.out.println("is tour now");
                break;
            case USER_MARK:
                System.out.println("is user now");
                break;
            default:
                System.out.println("is default now");
        }

    }

    @Test
    public void Test68() {
        String str = "spring";
        SeasonEnum s1 = SeasonEnum.valueOf(str.toUpperCase());
        System.out.println(s1);
        System.out.println(SeasonEnum.values());
        for(SeasonEnum s : SeasonEnum.values()){
            log.info("{}   [{}]", s, s.ordinal() );
            log.info("name    [{}]", s.name() );
        }
        log.info("compare    [{}]", SeasonEnum.SPRING.compareTo(SeasonEnum.SUMMER));


    }

    @Test
    public void Test83() {
        for(SexEnum s : SexEnum.values()){
            System.out.println(s.getKey() + "====" + s.getText());
        }

    }

    @Test
    public void Test91() {
        for(ProfileFrequencyEnum s : ProfileFrequencyEnum.values()){
            System.out.println(s.name() + "====" + s.getText());
        }

    }

}
