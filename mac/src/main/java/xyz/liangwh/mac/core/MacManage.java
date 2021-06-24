package xyz.liangwh.mac.core;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MAC地址管理
 */
public class MacManage {




    //OUI 组织唯一标识 MAC前缀
    private static String[] oui = new String[]{"18","08","22"};

    private final static int LENGTH = 12-oui.length*2;
    //最大值
    private final static long MAX_VALUE = 1l<<(LENGTH<<2);

    public static void main(String[] args) {

        System.out.println(MAX_VALUE);
        System.out.println(LENGTH<<2);
        System.out.println(1<<24);

        MacManage m = new MacManage();
        System.out.println(m.build(22));
        System.out.println(m.build(22,MacModel.UPPER_CASE));

    }

    /**
     * 解析mac地址
     * @param mac 格式 aa-bb-cc-dd-ee-ff
     * @return
     */
    public long decode(String mac){
        return 0;
    }
    /**
     * 构建mac地址
     * @param var
     * @param model
     * @return
     */
    public MacAddress build(long var,MacModel model){
        if (var<0||var>=MAX_VALUE){
            throw new IllegalArgumentException("计算MAC地址失败，当前入参["+var+"]已越界[0"+MAX_VALUE+")");
        }
        MacAddress address = new MacAddress();
        address.setOui(oui);
        address.setValue(var);
        String hex = Long.toHexString(var);
        hex = leftFix(hex);
        String[] arr = toArr(hex);
        address.setAddArr(arr);
        hex = StringUtils.join(arr,"-");
        address.setAddress(hex);
        if(model==MacModel.UPPER_CASE){
            address.setAddress(StringUtils.upperCase(hex));
        }
        return address;
    }


    public MacAddress build(long var){
        return build(var,MacModel.LOWER_CASE);
    }

    private String leftFix(String hex) {
        if(hex.length()<LENGTH){
            hex = StringUtils.leftPad(hex,LENGTH,"0");
        }
        return hex;
    }

    private   String[]  toArr(String s){
        List l =  new ArrayList(Arrays.asList(oui));
        String[] data = s.split("");
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<data.length;i++){
            sb.append(data[i]);
            if((i%2)==1){
                l.add(sb.toString());
                sb.delete(0,sb.length());
            }
        }
        return (String[]) l.toArray(new String[6]);
    }

}
