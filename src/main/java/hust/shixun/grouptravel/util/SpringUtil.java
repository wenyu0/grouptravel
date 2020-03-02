package hust.shixun.grouptravel.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

    /**
     * Desc :  spring工具类
     * 普通类调用Spring bean对象
     * 说明：此类需要放到主函数同包或者子包下才能被扫描，否则失效。
     */
    @Component
    public class SpringUtil implements ApplicationContextAware {
        private static ApplicationContext applicationContext = null;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            if (SpringUtil.applicationContext == null) {
                SpringUtil.applicationContext = applicationContext;
                System.out.println("==ApplicationContext配置成功,在普通类可以通过调用SpringUtil.getAppContext()获取applicationContext对象==");
            }
        }

        /**
         * Desc :  获取applicationContext
         * User : csc
         */

        public static ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        /**
         * Desc :  通过beanName获取bean
         */
        public static Object getBean(String beanName) {
            return getApplicationContext().getBean(beanName);
        }

        /**
         * Desc :  通过class获取bean
         */
        public static <T> T getBean(Class<T> clazz){
            return getApplicationContext().getBean(clazz);
        }


        /**
         * Desc : 通过name、class返回指定的bean
         */
        public static <T> T getBean(String name,Class<T> clazz){
            return getApplicationContext().getBean(name,clazz);
        }

    }
