package com.pms.mapper;

import java.util.List;
import java.util.Map;

import com.pms.entity.Inductioninfo;

import tk.mybatis.mapper.common.Mapper;

public interface InductioninfoMapper extends Mapper<Inductioninfo> {

	List<Map<String, Object>> getUserNameComboList();
}