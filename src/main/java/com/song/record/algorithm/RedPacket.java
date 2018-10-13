package com.song.record.algorithm;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 红包算法(以元为单位)
 */
public class RedPacket {

    /**
     * 生成红包最小值 1分
     */
    private static final double MIN_MONEY = 0.01F;

    /**
     * 生成红包最大值 200元
     */
    private static final double MAX_MONEY = 200*100F;

    /**
     * 小于最小值 -1
     */
    private static final int LESS = -1;

    /**
     * 大于最大值 -2
     */
    private static final int MORE = -2;

    /**
     * 正常值 1
     */
    private static final int OK = 1;

    /**
     * 最大的红包是平均值的TIMES倍，防止某一次分配红包过大
     */
    private static final double TIMES = 2.1F;

    /**
     * 递归次数
     */
    private int recursiveCount = 0;

    private List<Double> splitRedPacket(double money, int count){
        List<Double> moneys = new LinkedList<>();
        double totalMoney = money;

        //计算出最大的红包
        double max = (money / count)*TIMES;
        max = max > MAX_MONEY ? MAX_MONEY : max;
        max = getTwoDecimal(max);

        for(int i = 0;i < count;i++){
//            if(count-i == 1){
//                System.out.println("totalMoney:"+totalMoney+",money:"+money);
//                moneys.add(totalMoney - money);
//            }else{
                //随机获取红包
                double redPacket = randomRedPacket(money, MIN_MONEY, max, count-i);
                System.out.println(redPacket);
                moneys.add(redPacket);
                //总金额减少
                money -= redPacket;
//            }
//            redPacket = getTwoDecimal(redPacket);

        }

        return moneys;
    }

    private double randomRedPacket(double totalMoney, double minMoney, double maxMoney, int count){
        //如果只有一个红包就直接返回
        if(count == 1){
            return getTwoDecimal(totalMoney);
        }

        if(minMoney == maxMoney){
            return minMoney;
        }

        //如果最大金额大于了剩余金额，则用剩余金额，因为这个money每分配一次都会减小
        maxMoney = maxMoney > totalMoney ? totalMoney : maxMoney;

        //在minMoney到maxMoney生成一个随机红包
        double redPacket = Math.random()*(maxMoney - minMoney) + minMoney;
//        redPacket = getTwoDecimal(redPacket);

        double lastMoney = totalMoney - redPacket;

        int status = checkMoney(lastMoney, count-1);

        //正常金额
        if(status == OK){
            return getTwoDecimal(redPacket);
        }

        //如果生成的金额不合法，则递归重新生成
        if(status == LESS){
            recursiveCount++;
            System.out.println("递归次数:"+recursiveCount);
            return randomRedPacket(totalMoney, minMoney, redPacket, count);
        }

        if(status == MORE){
            recursiveCount++;
            System.out.println("递归次数:"+recursiveCount);
            return randomRedPacket(totalMoney, redPacket, maxMoney, count);
        }

        return getTwoDecimal(redPacket);
    }

    /**
     * 检验剩余的金额的平均值是否在最小值和最大值范围内
     * @param lastMoney
     * @param count
     * @return
     */
    private int checkMoney(double lastMoney, int count){
        double avg = lastMoney / count;
        if(avg < MIN_MONEY){
            return LESS;
        }
        if(avg > MAX_MONEY){
            return MORE;
        }
        return OK;
    }

    /**
     * 将数据保留两位小数
     * @param num
     * @return
     */
    private double getTwoDecimal(double num) {
        DecimalFormat dFormat=new DecimalFormat("#.00");
        String yearString=dFormat.format(num);
        Double temp= Double.valueOf(yearString);
        return temp;
    }

    public static void main(String[] args){
        RedPacket redPacket = new RedPacket();
        List<Double> moneys = redPacket.splitRedPacket(98.7, 2);
        System.out.println(moneys);
        double sum = 0F;
        for(double m : moneys){
            sum += m;
        }
        System.out.println(sum);
    }



}
