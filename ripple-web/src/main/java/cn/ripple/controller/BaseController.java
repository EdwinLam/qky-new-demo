package cn.ripple.controller;

import cn.ripple.utils.PageUtil;
import cn.ripple.service.BaseService;
import cn.ripple.vo.PageVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class BaseController<E, ID extends Serializable> {
    /**
     * 获取service
     * @return
     */
    @Autowired
    public abstract BaseService<E,ID> getService();

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过id获取")
    public E get(@PathVariable ID id){
        return getService().get(id);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    public List<E> getAll(){

        List<E> list = getService().getAll();
        return list;
    }

    @RequestMapping(value = "/getByPage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public Page<E> getByPage(@ModelAttribute PageVo page){
        return getService().findAll(PageUtil.initPage(page));
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public E save(@ModelAttribute E entity){
        return getService().save(entity);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public E update(@ModelAttribute E entity){
        return  getService().update(entity);
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除数据")
    public boolean delAll(@RequestBody List<E> entities){
        getService().delete(entities);
        return true;
    }

    @RequestMapping(value = "/delByIds",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过ids删除")
    public boolean delAllByIds(@RequestParam ID[] ids){
        for(ID id:ids){
            getService().delete(id);
        }
        return true;
    }
}
