package com.qy105.aaa.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qy105.aaa.page.PageInfos;
import com.qy105.aaa.util.Map2BeanUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/10 19:39
 * @description：
 * @modified By：
 */
public abstract class BaseService<T> {

    public abstract Mapper<T> getMapper();
    @Autowired
    private Mapper<T> mapper;
    private Class<T> cache = null; // 定义一个缓存--->来存储泛型
    /**
     * @author Seven Lee
     * @description
     *      通过主键集合进行批量删除
     *          数据库的主键名必须叫id
     * @param [ids]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer deleteBatch(Integer[] ids) {
        /**
         * List:是需要传入的参数
         *      <delete id="" paratemeterType=list
         *      where 主键 in (1,2,3,4,5,6,7,8...)
         */
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return getMapper().deleteByExample(example);
    }
    /**
     * @author Seven Lee
     * @description
     *      查询一条数据
     * @param [t]
     * @date 2020/3/13
     * @return T
     * @throws
     **/
    public T queryOne(T t) {
        return mapper.selectOne(t);
    }
    /**
     * @author Seven Lee
     * @description
     *      新增操作
     * @param [t]
     * @date 2020/3/12
     * @return java.lang.Integer
     * @throws
     **/
    public Integer add(T t) {
        return getMapper().insertSelective(t);
    }
    /**
     * @author Seven Lee
     * @description
     *      通过Map类型转换为Java实体对象
     * @param [map]
     * @date 2020/3/12
     * @return T
     * @throws
     **/
    public T newInstance(Map map) {
        // 自定义高性能反射工具类(有些是有泛型的(List,Map,Set,BaseService<T>....))
        return (T) Map2BeanUtil.map2Bean(map, getTypeArgument());
    }
    /**
     * @author Seven Lee
     * @description
     *      获取泛型
     * @param []
     * @date 2020/3/12
     * @return java.lang.Class<T>
     * @throws
     **/
    public Class<T> getTypeArgument() {
        if(null == cache) {
            cache = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]);
        }
        return cache;
    }
    /**
     * @author Seven Lee
     * @description
     *      查询列表信息(带条件查询)
     *      t缺省:说明t-->null
     *      那么就查询所有数据
     * @param [t]
     * @date 2020/3/13
     * @return java.util.List<T>
     * @throws
     **/
    public List<T> queryList(T t) {
        return mapper.select(t);
    }
    /**
     * @author Seven Lee
     * @description
     *      批量更新
     *      // TODO 后期维护
     * @param [t, ids, properties]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer updateBatch(T t, List<Object> ids, Object properties) {
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", ids)).build();
        return getMapper().updateByExample(t, example);
    }
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
     * @author Seven Lee
     * @description
     *      更新操作
     * @param [t]
     * @date 2020/3/12
     * @return java.lang.Integer
     * @throws
     **/
    public Integer updatek(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
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
     * @author Seven Lee
     * @description
     *      通过主键进行删除
     * @param [key]
     * @date 2020/3/13
     * @return java.lang.Integer
     * @throws
     **/
    public Integer deletek(T t) {
        return getMapper().deleteByPrimaryKey(t);
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
        PageInfo<T> tPageInfo = new PageInfo<T>();
        tPageInfo.setTotal(this.getCount(pageInfos.getT()));
        return tPageInfo;
    }


}
