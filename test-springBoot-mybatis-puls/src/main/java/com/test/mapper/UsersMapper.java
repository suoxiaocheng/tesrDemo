package com.test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.test.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 索方成
 * @since 2020-02-20
 */
public interface UsersMapper extends BaseMapper<Users> {

    IPage<Users> selectPage(IPage<Users> page, @Param(Constants.WRAPPER) Wrapper<Users> queryWrapper);
}
