package com.autumn.system.dao;
import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.autumn.system.tools.CommonTool;

@Aspect
@Component
public class SqlAspect{
	
	/**
	 * 查看执行的service层
	 * @param pjp
	 */
	@Before("execution(* com.autumn.*.services.*.*(..))")
	public void beforeService(JoinPoint pjp){
    	System.out.println("---Service层业务---"+CommonTool.getNowDateStr());
        System.err.println("  getTarget: " + pjp.getTarget().getClass());
        System.err.println("  toShortString: " + pjp.toShortString());
        System.err.println("  Args: " + Arrays.toString(pjp.getArgs()).toString());
	}
	
	/**
	 * 查看执行的sql
	 */
    @Before("execution(* com.autumn.system.dao.CommonDao..*(..))")
    public void before(JoinPoint pjp){
    	//打印参数
    	System.out.println("-------------参数--------------"+CommonTool.getNowDateStr());
    	Object[] argsObjects = pjp.getArgs();
        for (Object obj:argsObjects) {
        	if(obj!=null&&obj.getClass().isArray()){  //如果是数组
        		System.out.println(ArrayUtils.toString(obj));
        	}else {
    			System.out.println(obj);   //不是数组
			}
		}
    }
    
    /**
     * 查看sql执行时间
     */
    @Around("execution(* com.autumn.system.dao.CommonDao..*(..))")   /*切点 - 有Tag标记的Method*/
    public Object around(ProceedingJoinPoint pjp) throws Throwable {    /*增强*/
		long s = System.currentTimeMillis();
        Object result = pjp.proceed();
        long e = System.currentTimeMillis();
    	System.out.println("-------------时长--------------"+(e-s));
        return result;
    }

	public static void main(String[] args) {
		System.out.println("这是1个中文!!!");
	}
}
