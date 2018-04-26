package efan.com.money.staticfunction;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/14.
 */

public class StaticValue {
    //帐号ID
    public static final String USER_ID = "USER_ID";
    //接单跳转详情
    public static final int JYZ_TO_INDENT = 1;
    public static final int SHZ_TO_INDENT = 2;
    public static final int JYCG_TO_INDENT = 3;
    //发单跳转详情
    public static final int FD_JXS_TO_INDENT = 4;
    public static final int FD_DSH_TO_INDENT = 5;
    public static final int FD_JYCG_TO_INDENT = 6;
    //订单状态
    public static final String INDENT_NO = "INDENT_NO";
    public static final String INDENT_CENTER = "INDENT_CENTER";//订单进行中
    public static final String INDENT_CHECK = "INDENT_CHECK";//审核中or待审核
    public static final String INDENT_SUCCESS = "INDENT_SUCCESS";//交易成功
}
