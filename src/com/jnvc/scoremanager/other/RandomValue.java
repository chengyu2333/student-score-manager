package com.jnvc.scoremanager.other;

import java.util.HashMap;  
import java.util.Map;  
  
/** 
 * 随机生成中文姓名，性别，Email，手机号，住址 
 */  
public class RandomValue {  
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
    private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪";  
    private static String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";  
    private static String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航";  
    private static String[] road="济南市历下区解放路街道历山路社区,济南市历下区千佛山街道 ,济南市历下区千佛山街道司里街社区,济南市历下区千佛山街道佛山苑社区 ,济南市历下区千佛山街道千佛山西路社区,济南市历下区趵突泉街道青年东路社区,济南市历下区趵突泉街道泺文路社区,济南市历下区泉,锹方值?,济南市历下区泉,锹方值懒俸稚缜,济南市历下区大明湖街道舜井街社区,济南市历下区大明湖街道按,焖窘稚缜,济南市历下区大明湖街道县西巷社区,济南市历下区东,亟值?,济南市历下区东,亟值蓝,稚缜,济南市历下区东,亟值楞由缜,济南市历下区文东街道 ,济南市历下区文东街道文化东路 ,济南市历下区文东街道中创开元山庄社区,济南市历下区建新街道历山东路社区,济南市历下区建新街道绿景嘉园社区 ,济南市历下区甸柳街道甸柳新村第四社区,济南市历下区甸柳街道甸南社区 ,济南市历下区燕山街道和平路北社区,济南市历下区姚家镇仁合 ,济南市历下区姚家镇浆水泉庄,济南市历下区姚家镇林家庄,济南市历下区姚家镇徐家村,济南市历下区姚家镇八涧堡村,济南市历下区姚家镇刘智远村 ,济南市市中区大,墼敖值滥篮驮飞缜,济南市市中区大,墼敖值谰飞缜,济南市市中区杆石桥街道启明里社区 ,济南市市中区杆石桥街道胜南新华印刷,Ъ沂羟,济南市市中区四里村街道建设路社区,济南市市中区四里村街道英雄山社区 ,济南市市中区四里村街道济南电信局家属区,济南市市中区魏家庄街道 ,济南市市中区魏家庄街道人民商,∩缜,济南市市中区二七街道 ,济南市市中区二七街道重汽,,登,Ъ沂羟,济南市市中区七里山街道园丁小区家属区,济南市市中区七里山街道郎南社区 ,济南市市中区六里山街道六里山南路社区,济南市市中区六里山街道玉函北区社区 ,济南市市中区六里山街道市中区机,囟奚峒沂羟,济南市市中区舜玉路街道舜中社区,济南市市中区舜玉路街道东八社区,济南市市中区舜玉路街道省交通厅家属区,济南市市中区舜玉路街道舜玉花园家属区,济南市市中区舜玉路街道武警宿舍家属区,济南市市中区舜玉路街道舜绣社区,济南市市中区泺源街道青年西路社区,济南市市中区王,僮值,济南市市中区王,僮值狼嗔缴缜,济南市市中区王,僮值烙⒒飞缜?,济南市市中区王,僮值朗〉缌ι璞,奚峒沂羟,济南市市中区舜耕街道 ,济南市市中区白马山街道袁柳西居,济南市市中区白马山街道东红庙社区,济南市市中区白马山街道尹家堂社区,济南市市中区白马山街道后魏华庄社区,济南市市中区七贤街道井家,瞪缜,济南市市中区七贤街道九曲庄社区,济南市市中区,家庄镇丘山小区,济南市市中区,家庄镇董庄村,济南市市中区,家庄镇马家庄村,济南市市中区,家庄镇双庙屯村,济南市市中区,家庄镇小庄村,济南市市中区,家庄镇刘家林村,济南市市中区,家庄镇枣林村,济南市市中区,家庄镇土屋村,济南市市中区,家庄镇东渴马村,济南市市中区,家庄镇,村东村,济南市市中区,家庄镇蛮子村,济南市市中区十六里河镇石崮村 ,济南市市中区十六里河镇东十六里河村,济南市市中区十六里河镇分水岭村,济南市市中区十六里河镇北康而庄村,济南市市中区十六里河镇白土岗村,济南市市中区十六里河镇搬,井村,济南市市中区十六里河镇,椅哑麓,济南市槐荫区振兴街街道 ,济南市槐荫区振兴街街道丁字山社区,东营市利津县虎,乡 ,济南市槐荫区中大槐树街道裕北社区,济南市槐荫区道德街街道西新街社区,济南市槐荫区西市,〗值阑缜,济南市槐荫区五里,到值,济南市槐荫区营市街街道 ,济南市槐荫区营市街街道槐村街社区 ,济南市槐荫区青年,敖值阑币,,∩缜,济南市槐荫区南辛庄街道南辛北街社区,济南市槐荫区段店北路街道孔村社区,济南市槐荫区段店北路街道孔村,济南市槐荫区张庄路街道明星社区,济南市槐荫区张庄路街道大饮马 ,济南市槐荫区匡山街道老屯东路社区,济南市槐荫区匡山街道老屯 ,济南市槐荫区美里湖街道新,社区,济南市槐荫区美里湖街道,唐,济南市槐荫区美里湖街道范庄,济南市槐荫区吴家堡镇 ,济南市槐荫区吴家堡镇西堡,济南市槐荫区吴家堡镇唐庄,济南市槐荫区吴家堡镇大高,济南市槐荫区吴家堡镇刘庄,济南市槐荫区吴家堡镇大杨,济南市槐荫区吴家堡镇三教堂,济南市槐荫区吴家堡镇申庄 ,济南市槐荫区段店镇北八里社区,济南市槐荫区段店镇大杨,济南市槐荫区段店镇王府,济南市槐荫区段店镇演马,济南市槐荫区段店镇睦里,济南市槐荫区段店镇东谢屯,济南市槐荫区段店镇小杨,济南市槐荫区段店镇筐里,济南市槐荫区段店镇油赵,济南市天桥区无影山街道 ,济南市天桥区无影山街道后黄屯社区,济南市天桥区天桥东街街道义和社区,济南市天桥区北村街道北村西区社区,济南市天桥区南村街道北区社区 ,济南市天桥区堤口路街道无影山中路社区,济南市天桥区北,街道,济南市天桥区制锦市街道 ,济南市天桥区制锦市街道,艚稚缜,济南市天桥区宝华街道 ,济南市天桥区,僭值,僭薪稚缜,济南市天桥区,僭值乐聘锝稚缜,济南市天桥区,北路街道聚贤街社区,济南市天桥区药山街道 ,济南市天桥区药山街道太平社区,济南市天桥区药山街道卢庄社区,济南市天桥区药山街道天福苑社区,济南市天桥区北园街道黄桥社区,济南市天桥区北园街道凤凰山社区,济南市天桥区北园街道杨庄社区,济南市天桥区北园街道联四社区,济南市天桥区北园街道黄台社区,济南市天桥区北园街道幸福社区,济南市天桥区北园街道明园社区,济南市天桥区北园街道舜清苑社区,济南市天桥区泺口街道林桥社区,济南市天桥区泺口街道崔庙社区,济南市天桥区泺口街道南徐社区,济南市天桥区泺口街道袁庄社区,济南市天桥区泺口街道泺东社区 ,济南市天桥区桑梓店镇桑梓店镇小马 ,济南市天桥区桑梓店镇桑梓店镇,菝硗醮,济南市天桥区桑梓店镇桑梓店镇丁庄村,济南市天桥区桑梓店镇桑梓店镇冯堂村,济南市天桥区桑梓店镇桑梓店镇街后村,济南市天桥区桑梓店镇桑梓店镇刘庙村,济南市天桥区桑梓店镇桑梓店镇彭庄,济南市天桥区桑梓店镇桑梓店镇,李村,济南市天桥区桑梓店镇桑梓店镇小吕村,济南市天桥区桑梓店镇桑梓店镇小寨,济南市天桥区桑梓店镇桑梓店镇左庄村,济南市天桥区大桥镇八里,济南市天桥区大桥镇大王庙".split(",");
    private static String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");  
      
    public static int getNum(int start,int end) {  
        return (int)(Math.random()*(end-start+1)+start);  
    }  
      
    /** 
     * 返回Email 
     * @param lMin 最小长度 
     * @param lMax 最大长度 
     * @return 
     */  
    public static String getEmail(int lMin,int lMax) {  
        int length=getNum(lMin,lMax);  
        StringBuffer sb = new StringBuffer();       
        for (int i = 0; i < length; i++) {       
            int number = (int)(Math.random()*base.length());  
            sb.append(base.charAt(number));       
        }  
        sb.append(email_suffix[(int)(Math.random()*email_suffix.length)]);  
        return sb.toString();     
    }  
  
    /** 
     * 返回手机号码 
     */  
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");  
    public static String getTel() {  
        int index=getNum(0,telFirst.length-1);  
        String first=telFirst[index];  
        String second=String.valueOf(getNum(1,888)+10000).substring(1);  
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);  
        return first+second+thrid;  
    }  
      
    /** 
     * 返回中文姓名 
     */  
    public static String name_sex = "";  
    public static String getChineseName() {  
        int index=getNum(0, firstName.length()-1);  
        String first=firstName.substring(index, index+1);  
        int sex=getNum(0,1);  
        String str=boy;  
        int length=boy.length();  
        if(sex==0){  
            str=girl;  
            length=girl.length();  
            name_sex = "女";  
        }else {  
            name_sex="男";  
        }  
        index=getNum(0,length-1);  
        String second=str.substring(index, index+1);  
        int hasThird=getNum(0,1);  
        String third="";  
        if(hasThird==1){  
            index=getNum(0,length-1);  
            third=str.substring(index, index+1);  
        }  
        return first+second+third;  
    }  
      
    /** 
     * 返回地址 
     * @return 
     */  
    public static String getRoad() {  
        int index=getNum(0,road.length-1);  
        String first=road[index];  
        String second=String.valueOf(getNum(11,150))+"号";  
        return first+second;  
    }
      
      
    public static void main(String[] args) {  
        //for (int i = 0; i < 100; i++) {  
        	System.out.println("年龄："+getNum(10, 20));
        	System.out.println("姓名："+getChineseName());
        	System.out.println("电话："+getTel());
        	System.out.println("街道:"+getRoad());
            System.out.println("邮件："+getEmail(6,9));  
        //}  
    }  
}  