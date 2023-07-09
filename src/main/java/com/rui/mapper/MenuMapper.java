package com.rui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rui.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}