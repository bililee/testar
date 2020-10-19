package com.lee.testar.mapper;

import com.lee.testar.model.TidDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TidMapper {


    TidDO selectByid(@Param("id") Integer id);
}
