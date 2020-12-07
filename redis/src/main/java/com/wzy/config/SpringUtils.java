package com.wzy.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/7
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
@Component

public class SpringUtils implements ApplicationContextAware{

        private static ApplicationContext applicationContext;

        /**
         * 设置spring上下文
         *
         * @param context spring上下文
         * @throws BeansException
         */
        @Override
        public void setApplicationContext(ApplicationContext context) throws BeansException {
            applicationContext = context;
        }

        /**
         * 获取容器
         *
         * @return
         */
        public static ApplicationContext getApplicationContext() {
            return applicationContext;
        }

        /**
         * 获取容器对象
         *
         * @param type
         * @param <T>
         * @return
         */
        public static <T> T getBean(Class<T> type) {
            return applicationContext.getBean(type);
        }


        /**
         * 获取当前环境
         * @return
         */
        public static String getActiveProfile() {
            return applicationContext.getEnvironment().getActiveProfiles()[0];
        }

}
