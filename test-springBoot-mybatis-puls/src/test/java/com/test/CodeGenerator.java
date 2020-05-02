package com.test;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        //1.创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //2.创建全局配置对象
        GlobalConfig globalConfig = new GlobalConfig();
        //获取当前项目的磁盘路径
        String property = System.getProperty("user.dir");
        //设置生成代码输入路径
        globalConfig.setOutputDir(property+"/src/main/java");
        //设置作者名称
        globalConfig.setAuthor("索方成");
        //设置是否打开目录
        globalConfig.setOpen(true);
        //设置生成器service接口的名称 %s表示占位符  用实体类的名称替换占位符
        globalConfig.setServiceName("%sService");
        //设置生成service的实现类的名称
        globalConfig.setServiceImplName("%sServiceImpl");
        //设置生成Mapper接口的名称
        globalConfig.setMapperName("%sMapper");
        //设置生成Mapper映射文件的名称
        globalConfig.setXmlName("%sMapper");
        //设置是否覆盖已存在的文件
        globalConfig.setFileOverride(true);
        //设置是否开启二级缓存
        globalConfig.setEnableCache(false);
        //设置是否生成reSultMap
        globalConfig.setBaseResultMap(true);
        //设置自动生成器的配置
        autoGenerator.setGlobalConfig(globalConfig);

        //3.创建数据源配置对象
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //设置数据库驱动类名称
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        //设置数据库访问的URL
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        //设置数据库用户名
        dataSourceConfig.setUsername("root");
        //设置数据库密码
        dataSourceConfig.setPassword("root");
        //设置自动生成器的数据源
        autoGenerator.setDataSource(dataSourceConfig);

        //4.创建包的配置对象
        PackageConfig packageConfig = new PackageConfig();
        //设置父级包
        packageConfig.setParent("com.test");
        //设置实体类的包名
        packageConfig.setEntity("pojo");
        //设置业务成的包名
        packageConfig.setService("service");
        //设置业务层实现类的包名
        packageConfig.setServiceImpl("service.impl");
        //设置控制层的包名
        packageConfig.setController("controller");
        //设置自动生成器的包名
        autoGenerator.setPackageInfo(packageConfig);

        //5.创建自定义配置对象
        InjectionConfig injectionConfig = new InjectionConfig(){
            @Override
            public void initMap() {
            }
        };
        //创建自定义输出的配置集合
        ArrayList<FileOutConfig> objects = new ArrayList<>();
        //设置自定义输出的配置路径
        String template="/mybatis/mapper.xml.vm";
        objects.add(new FileOutConfig(template) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return property+"/src/main/resources/mapper/"+tableInfo.getEntityName()
                        +"Mapper"+
                        StringPool.DOT_XML;
            }
        });
        //设置自定义配置对象的输出配置集合
        injectionConfig.setFileOutConfigList(objects);
        //设置自动生成器的自定义配置
        autoGenerator.setCfg(injectionConfig);
        //设置模板
        autoGenerator.setTemplate(new TemplateConfig().setXml(null));

        //6.创建生成策略配置对象
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置实体属性命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //设置实体类使用Lombok
        strategyConfig.setEntityLombokModel(true);
        //设置使用RestController
        strategyConfig.setRestControllerStyle(true);
        //设置使用自动生成表名
        strategyConfig.setInclude("users");
        //设置Controller是否使用驼峰命名法
        strategyConfig.setControllerMappingHyphenStyle(true);
        //设置自动生成器的生成配置策略
        autoGenerator.setStrategy(strategyConfig);

        //执行生成策略
        autoGenerator.execute();

    }
}
