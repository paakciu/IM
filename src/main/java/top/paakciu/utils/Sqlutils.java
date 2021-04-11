package top.paakciu.utils;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.paakciu.config.IMConfig;
import top.paakciu.protocal.packet.PullMessageRequestPacket;


import java.io.IOException;

import java.util.List;
import java.util.function.Function;

/**
 * @author paakciu
 * @ClassName: Sqlutils
 * @date: 2021/3/17 16:14
 */
public class Sqlutils {

    private static volatile SqlSessionFactory INSTANCE;

    private Sqlutils() {
    }

    //使用双重检测单例
    public static SqlSessionFactory getInstance() throws IOException {
        if (INSTANCE == null) {
            synchronized (SqlSessionFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SqlSessionFactoryBuilder()
                            .build(Resources.getResourceAsStream(IMConfig.MYBATISCONFIGURATION));
                }
            }
        }
        return INSTANCE;
    }

    public static <T,R> R startSqlSession(Class<T> clazz, Function<T,R> function){
        R ref=null;
        try (SqlSession sqlSession=Sqlutils.getInstance().openSession())
        {
            T mapper = sqlSession.getMapper(clazz);
            ref=function.apply(mapper);

            //这里一定要提交
            sqlSession.commit();
        } catch (IOException e) {
            System.out.println("服务器中配置数据库的configuration.xml路径错误");
            //e.printStackTrace();
        }catch (PersistenceException e) {
            System.out.println(clazz+"数据库执行"+function+"方法时失败");
            //e.printStackTrace();
        }finally {
        }
        return ref;
    }
    public interface SqlListener<R>{
        R onSqlSessonOpen(SqlSession sqlSession);
    }
    public static <R> R startSqlSessionByListener(SqlListener<R> sqlListener){
        R ref=null;
        try (SqlSession sqlSession=Sqlutils.getInstance().openSession())
        {
            ref=sqlListener.onSqlSessonOpen(sqlSession);
            //这里一定要提交
            sqlSession.commit();
        } catch (IOException e) {
            System.out.println("服务器中配置数据库的configuration.xml路径错误");
            //e.printStackTrace();
        }catch (PersistenceException e) {
            System.out.println("数据库执行"+sqlListener+"接口方法时失败");
            //e.printStackTrace();
        }finally {
        }
        return ref;
    }


    public static <T,R> R startSqlSessionWithoutPersistenceExceptionCatch(Class<T> clazz, Function<T,R> function) throws PersistenceException {
        R ref=null;
        try (SqlSession sqlSession=Sqlutils.getInstance().openSession())
        {
            T Mapper = sqlSession.getMapper(clazz);
            ref=function.apply(Mapper);
            //这里一定要提交
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ref;
    }
}
