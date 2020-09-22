package xyz.liangwh.mac.core;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MAC地址管理
 */
public class MacManage {



    //最大值
    private final static long MAX_VALUE = 1L<<32;
    private final static int LENGTH = 8;
    //OUI 组织唯一标识 MAC前缀
    private static String[] oui = new String[]{"08","22"};



    public static void main(String[] args) {


        MacManage m = new MacManage();
        System.out.println(m.build(4294967295L));
        System.out.println(m.build(4294967295L,MacModel.UPPER_CASE));

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

    public  String[]  toArr(String s){
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
