package efan.com.money.Bean;

/**
 * 作者： ZlyjD.
 * 时间：2018/4/26.
 */

public class NetDingDanBean {
    private int ddid;// 订单id
    private int User_Jd_Id;// 接单的用户id
    private int Fd_Id;// private int fdid;
    private String Dd_ZhuangTai;
    private String Dd_ShenHe_iv1;
    private String Dd_ShenHe_iv2;
    private String Dd_Time;
    private int User_Fd_Id;// 发单的用户的id
    private String TuiGuang;
    private String ZhangHao;
    private String Fd_MingCheng;
    private String Fd_YaoQiu;
    private String Fd_JiaGe;
    private String Fd_ZongShu;
    private String Fd_NeiRong;
    private String Fd_BeiZhu;
    private String Fd_ZhuangTai;
    private String ZhiFuWay;
    private String Fd_Time;
    /**
     * 如果查询的为发单 则返回接单的头像
     * <p>
     * 如果查询的为接单 则返回发单人的头像
     */

    private String head;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDdid() {
        return ddid;
    }

    public void setDdid(int ddid) {
        this.ddid = ddid;
    }

    public int getUser_Jd_Id() {
        return User_Jd_Id;
    }

    public void setUser_Jd_Id(int user_Jd_Id) {
        User_Jd_Id = user_Jd_Id;
    }

    public int getFd_Id() {
        return Fd_Id;
    }

    public void setFd_Id(int fd_Id) {
        Fd_Id = fd_Id;
    }

    public String getDd_ZhuangTai() {
        return Dd_ZhuangTai;
    }

    public void setDd_ZhuangTai(String dd_ZhuangTai) {
        Dd_ZhuangTai = dd_ZhuangTai;
    }

    public String getDd_ShenHe_iv1() {
        return Dd_ShenHe_iv1;
    }

    public void setDd_ShenHe_iv1(String dd_ShenHe_iv1) {
        Dd_ShenHe_iv1 = dd_ShenHe_iv1;
    }

    public String getDd_ShenHe_iv2() {
        return Dd_ShenHe_iv2;
    }

    public void setDd_ShenHe_iv2(String dd_ShenHe_iv2) {
        Dd_ShenHe_iv2 = dd_ShenHe_iv2;
    }

    public String getDd_Time() {
        return Dd_Time;
    }

    public void setDd_Time(String dd_Time) {
        Dd_Time = dd_Time;
    }

    public int getUser_Fd_Id() {
        return User_Fd_Id;
    }

    public void setUser_Fd_Id(int user_Fd_Id) {
        User_Fd_Id = user_Fd_Id;
    }

    public String getTuiGuang() {
        return TuiGuang;
    }

    public void setTuiGuang(String tuiGuang) {
        TuiGuang = tuiGuang;
    }

    public String getZhangHao() {
        return ZhangHao;
    }

    public void setZhangHao(String zhangHao) {
        ZhangHao = zhangHao;
    }

    public String getFd_MingCheng() {
        return Fd_MingCheng;
    }

    public void setFd_MingCheng(String fd_MingCheng) {
        Fd_MingCheng = fd_MingCheng;
    }

    public String getFd_YaoQiu() {
        return Fd_YaoQiu;
    }

    public void setFd_YaoQiu(String fd_YaoQiu) {
        Fd_YaoQiu = fd_YaoQiu;
    }

    public String getFd_JiaGe() {
        return Fd_JiaGe;
    }

    public void setFd_JiaGe(String fd_JiaGe) {
        Fd_JiaGe = fd_JiaGe;
    }

    public String getFd_ZongShu() {
        return Fd_ZongShu;
    }

    public void setFd_ZongShu(String fd_ZongShu) {
        Fd_ZongShu = fd_ZongShu;
    }

    public String getFd_NeiRong() {
        return Fd_NeiRong;
    }

    public void setFd_NeiRong(String fd_NeiRong) {
        Fd_NeiRong = fd_NeiRong;
    }

    public String getFd_BeiZhu() {
        return Fd_BeiZhu;
    }

    public void setFd_BeiZhu(String fd_BeiZhu) {
        Fd_BeiZhu = fd_BeiZhu;
    }

    public String getFd_ZhuangTai() {
        return Fd_ZhuangTai;
    }

    public void setFd_ZhuangTai(String fd_ZhuangTai) {
        Fd_ZhuangTai = fd_ZhuangTai;
    }

    public String getZhiFuWay() {
        return ZhiFuWay;
    }

    public void setZhiFuWay(String zhiFuWay) {
        ZhiFuWay = zhiFuWay;
    }

    public String getFd_Time() {
        return Fd_Time;
    }

    public void setFd_Time(String fd_Time) {
        Fd_Time = fd_Time;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
