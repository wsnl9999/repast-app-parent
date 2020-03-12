package com.qy105.aaa.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qy105.aaa.page.PageInfos;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/10 19:39
 * @description：
 * @modified By：
 */
public abstract class BaseService<T> {

    public abstract Mapper<T> getMapper();

    /**
     * create by: ws
     * description: 向数据库添加数据
     * create time: 19:52 2020/3/10
     * * @Param: null
     * @return 
     */
    protected Integer insert(T t) throws Exception {
        return getMapper().insert(t);
    }
    /**
     * create by: ws
     * description: 根据主键进行修改
     * create time: 19:54 2020/3/10
     * * @Param: null
     * @return 
     */
    protected Integer update(T t) throws Exception{
        return getMapper().updateByPrimaryKey(t);
    }
    /**
     * create by: ws
     * description: 根据主键进行删除
     * create time: 19:57 2020/3/10
     * * @Param: null
     * @return
     */
    protected Integer deleteByPrimaryKey(Object key) throws  Exception{
        return getMapper().deleteByPrimaryKey(key);
    }
    /**
     * create by: ws
     * description: 通过实体类的属性进行删除
     * create time: 19:59 2020/3/10
     * * @Param: null
     * @return
     */
    protected Integer delete(T t) throws Exception{
        return getMapper().delete(t);
    }
    /**
     * create by: ws
     * description: 根据唯一键查询
     * create time: 20:03 2020/3/10
     * * @Param: null
     * @return
     */
    protected T get(Object key) throws Exception{
        return getMapper().selectByPrimaryKey(key);
    }
    /**
     * create by: ws
     * description: 查询所有
     * create time: 20:04 2020/3/10
     * * @Param: null
     * @return
     */
    protected List<T> getAll() throws Exception{
        return getMapper().selectAll();
    }
    /**
     * create by: ws
     * description: 根据实体类属性进行查询
     * create time: 20:05 2020/3/10
     * * @Param: null
     * @return
     */
    protected T getOne(T t) throws Exception{
        return getMapper().selectOne(t);
    }
    /**
     * create by: ws
     * description: 根据实体类属性进行查询，返回集合列表
     * create time: 20:07 2020/3/10
     * * @Param: null
     * @return 
     */
    protected List<T> getModel(T t) throws Exception{
        return getMapper().select(t);
    }
    /**
     * create by: ws
     * description:  带分页的条件查询
     *               offset:偏移量(当前页码数)
     *               limit:查询多少条
     *
     * create time: 20:11 2020/3/10
     * * @Param: null
     * @return
     */
    protected List<T> getPage(T t, PageInfos<T> pageInfos) throws Exception{
        return getMapper().selectByRowBounds(t,new RowBounds(pageInfos.getPageNum(),pageInfos.getPageSize()));
    }
    /**
     * create by: ws
     * description:  查询数据(带条件)
     *               如果不需要条件，则直接传null
     * create time: 20:17 2020/3/10
     * * @Param: null
     * @return 
     */
    protected Integer getCount(T t) throws Exception {
        return getMapper().selectCount(t);
    }
    /**
     * create by: ws
     * description: TODO
     * create time: 20:25 2020/3/10
     * * @Param: null
     * @return
     */
    protected PageInfo<T> getPageInfo(PageInfos<T> pageInfos) throws Exception {
        if (pageInfos.getPageNum() == null) {
            pageInfos.setPageNum(0);
        }
        PageHelper.startPage(pageInfos.getPageNum(),pageInfos.getPageSize());
        List<T> model = this.getModel(pageInfos.getT());
        PageInfo<T> tPageInfo = new PageInfo<>();
        tPageInfo.setTotal(this.getCount(pageInfos.getT()));
        return tPageInfo;
    }


    }
