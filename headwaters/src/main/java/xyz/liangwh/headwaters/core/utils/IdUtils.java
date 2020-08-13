package xyz.liangwh.headwaters.core.utils;

public class IdUtils {

    /**
     * 生成可溯源id
     * @param id hw_mark 主键
     * @param insideId 当前号段中获取的值
     * @return
     */
    public  static long makeTrueId(long id,int insideId){
        long lid = id;
        return (id<<32)|insideId;
    }

    /**
     * 溯源
     * @param tid
     * @return
     */
    public  static int getMarkId(long tid){
        long id = tid>>32;
        return (int)id;
    }

}
