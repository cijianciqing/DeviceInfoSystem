package jsjzx.wlyw.networkmaintenance.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

//    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //返回登录页面
        registry.addViewController("/").setViewName("login");
        //为什么/login不好用
        registry.addViewController("/login00").setViewName("login");
        //登录成功后返回页面
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/welcome").setViewName("welcome");


        //显示设备列表
        registry.addViewController("/device/list").setViewName("device/list");
        //显示公司列表
        registry.addViewController("/company/list").setViewName("company/list");
        //显示厂家设备列表
        registry.addViewController("/tAm/list").setViewName("tAm/list");
        //显示设备具体型号
        registry.addViewController("/dmodel/list").setViewName("dmodel/list");

    }

   /* @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

//暂时禁用，其他功能测试完成后再启用
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        springboot已经做好静态资源映射
        registry.addInterceptor(new MyLoginInterceptor()).addPathPatterns("/*")
                .excludePathPatterns("/","/login","/login.html");
    }*/
}
