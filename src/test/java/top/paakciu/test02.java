//package top.paakciu;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import top.paakciu.mbg.mapper.UserMapper;
//import top.paakciu.mbg.model.User;
//import top.paakciu.mbg.model.UserExample;
//
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
///**
// * @author paakciu
// * @ClassName: test02
// * @date: 2021/3/16 12:36
// */
//public class test02 {
//    public static void main(String[] args) throws IOException {
//        // 定义配置文件，相对路径，文件直接放在resources目录下
//        String resource = "configuration.xml";
//        // 读取文件字节流
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//
//        // mybatis 读取字节流，利用XMLConfigBuilder类解析文件
//        // 将xml文件解析成一个 org.apache.ibatis.session.Configuration 对象
//        // 然后将 Configuration 对象交给 SqlSessionFactory 接口实现类 DefaultSqlSessionFactory 管理
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//
//        //补充：每条线程都应该有它自己的sqlsession实例
//        // openSession 有多个重载方法， 比较重要几个是
//        // 1 是否默认提交 SqlSession openSession(boolean autoCommit)
//        // 2 设置事务级别 SqlSession openSession(TransactionIsolationLevel level)
//        // 3 执行器类型   SqlSession openSession(ExecutorType execType)
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        /*
//        try(SqlSession sqlSession2 = sqlSessionFactory.openSession();){
//
//        }finally {
//
//        }
//        */
//
//        // mybatis 内部其实已经解析好了 mapper 和 mapping 对应关系，放在一个map中，这里可以直接获取
//        // 如果看源码可以发现userMapper 其实是一个代理类MapperProxy，
//        // 通过 sqlSession、mapperInterface、mechodCache三个参数构造的
//        // MapperProxyFactory 类中 newInstance(MapperProxy<T> mapperProxy)方法
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//
//        /* insert */
//        User user = new User();
//        user.setUsername("paakciu");
//        user.setPassword("123456");
//        userMapper.insert(user);
//        // 由于默认 openSession() 事务是交由开发者手动控制，所以需要显示提交
//        sqlSession.commit();
//
//        /* select */
//        List<User> users = userMapper.selectByExample(new UserExample());
//        System.out.println(users);
//
//        sqlSession.close();
//    }
//}
