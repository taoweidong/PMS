package com.pms.mapper;

import java.util.List;

import com.pms.entity.Notice;

import tk.mybatis.mapper.common.Mapper;

public interface NoticeMapper extends Mapper<Notice> {

	List<String> queryAuthor();
}