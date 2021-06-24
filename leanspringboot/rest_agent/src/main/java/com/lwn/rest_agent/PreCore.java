package com.lwn.rest_agent;

import org.apache.commons.lang3.StringUtils;

import java.lang.instrument.Instrumentation;


public class PreCore {



    public static void premain(String agentArgs, Instrumentation inst) {

        Const.pringInfo();
        System.out.println("target-enhancement-class :");
        System.out.println(agentArgs);
        if(agentArgs!=null){
            agentArgs = agentArgs.trim();
            inst.addTransformer(new DefineTransformer(agentArgs), true);
        }else{
            inst.addTransformer(new DefineTransformer("org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration$LoadBalancerInterceptorConfig"), true);
        }
    }




    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            try {
                i=i/0;
                if(i==5){

                    break;
                }
            }finally {
                System.out.println(i);
            }
        }
    }
}
