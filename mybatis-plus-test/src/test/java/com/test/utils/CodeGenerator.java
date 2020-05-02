package com.test.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        //创建自动生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //创建全局配置对象
        GlobalConfig globalConfig = new GlobalConfig();
        //获取当前项目磁盘路径
        String projectPath = System.getProperty("user.dir");
        //设置生成代码输出路径
        globalConfig.setOutputDir(projectPath+"/src/main/java");
        //设置作者名称
        globalConfig.setAuthor("zhuhongdan");
        //设置是否打开目录
        globalConfig.setOpen(false);
        //设置生成的service接口名称  %s是一个占位符  会用实体类的名字替换该占位符
        globalConfig.setServiceName("%sService");
        //设置生成的service实现类名称
        globalConfig.setServiceImplName("%sServiceImpl");
        //设置生成的mapper接口名称
        globalConfig.setMapperName("%sMapper");
        //设置生成的mapper映射文件名称
        globalConfig.setXmlName("%sMapper");
        //设置是否覆盖已存在文件
        globalConfig.setFileOverride(true);
        //设置是否开启二级缓存
        globalConfig.setEnableCache(false);
        //设置是否生成resultMap
        globalConfig.setBaseResultMap(true);
        //设置是否生成列的列表
        globalConfig.setBaseColumnList(false);
        //设置自动生成器的全局配置
        autoGenerator.setGlobalConfig(globalConfig);

        //创建数据源配置对象
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //设置数据库驱动类名称
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        //设置数据库访问Url
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        //设置数据库用户名
        dataSourceConfig.setUsername("root");
        //设置数据库密码
        dataSourceConfig.setPassword("root");
        //设置自动生成器的数据源配置
        autoGenerator.setDataSource(dataSourceConfig);

        //创建包的配置对象
        PackageConfig packageConfig = new PackageConfig();
        //设置父级包名
        packageConfig.setParent("com.test");
        //设置实体类的包名
        packageConfig.setEntity("pojo");
        //设置service的包名
        packageConfig.setService("service");
        //设置service实现类的包名
        packageConfig.setServiceImpl("service.impl");
        //设置controller的包名
        packageConfig.setController("controller");
        //设置自动生成器的包配置对象
        autoGenerator.setPackageInfo(packageConfig);

        //创建自定义配置对象
        InjectionConfig injectionConfig = new InjectionConfig(){
            @Override
            public void initMap() {

            }
        };
        //创建自定义输出配置集合
        ArrayList<FileOutConfig> fileOutConfigs = new ArrayList<>();
        //设置自定义输出配置的路径
        fileOutConfigs.add(new FileOutConfig("/templates/mybatis/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath+"/src/main/resources/mapper/"+tableInfo.getEntityName()
                        +"Mapper"+ StringPool.DOT_XML;
            }
        });
        //设置自定义配置对象的输出配置集合
        injectionConfig.setFileOutConfigList(fileOutConfigs);
        //设置自动生成器的自定义配置
        autoGenerator.setCfg(injectionConfig);
        //设置模板
        autoGenerator.setTemplate(new TemplateConfig().setXml(null));

        //创建生成策略配置对象
        StrategyConfig strategyConfig=new StrategyConfig();
        //设置实体属性命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //设置实体类使用Lombok
        strategyConfig.setEntityLombokModel(true);
        //设置使用RestController
        strategyConfig.setRestControllerStyle(true);
        //设置需要自动生成的表名
        strategyConfig.setInclude("user");
        //设置Controller是否使用驼峰命名
        strategyConfig.setControllerMappingHyphenStyle(true);
        //设置自动生成器的生成策略配置
        autoGenerator.setStrategy(strategyConfig);
        //执行自动生成
        autoGenerator.execute();
    }
}
