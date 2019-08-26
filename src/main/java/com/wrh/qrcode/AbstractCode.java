package com.wrh.qrcode;

class AbstractCode {
    protected static final String HEADER_51 = "51";
    protected static final String HEADER_52 = "52";
    protected static final String HEADER_53 = "53";
    protected static final int MIN_LENGTH = 54;

    /**
     * 码转化
     *
     * @param type 码类型
     * @return 码类型
     */
    protected static String codeType(String type) {
        String code;
        switch (type) {
            case HEADER_51:
                code = "1";
                break;
            case HEADER_52:
                code = "2";
                break;
            case HEADER_53:
                code = "3";
                break;
            default:
                code = "1";
                break;
        }

        return code;
    }
}
