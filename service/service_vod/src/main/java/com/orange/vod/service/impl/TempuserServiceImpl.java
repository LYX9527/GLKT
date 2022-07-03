package com.orange.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.vod.domain.Tempuser;
import com.orange.vod.service.TempuserService;
import com.orange.vod.mapper.TempuserMapper;
import org.springframework.stereotype.Service;

/**
* @author yilantingfeng
* @description 针对表【tempuser(临时用户表)】的数据库操作Service实现
* @createDate 2022-07-02 21:17:21
*/
@Service
public class TempuserServiceImpl extends ServiceImpl<TempuserMapper, Tempuser>
    implements TempuserService{

}




