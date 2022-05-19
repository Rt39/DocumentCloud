package com.autumn.common.service;

import com.autumn.platform.mapper.UserinfoMapper;
import com.autumn.platform.model.Userinfo;
import com.autumn.platform.model.UserinfoExample;
import com.autumn.system.dao.CommonDao;
import com.autumn.system.entitys.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/***
 * 通用类
 */
@Service
public class CommonService {

    @Resource(name="commonDao")
    CommonDao dao;

    @Autowired
    UserinfoMapper userinfoMapper;

    /**
     * 根据id查询后台用户
     * @param userId
     * @return
     */
    public Userinfo getUserinfoByID(Integer userId){
        return userinfoMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据用户名密码返回用户实体
     * @param userinfo
     * @return
     */
    public List<Userinfo> getUserinfoByAccount(Userinfo userinfo){
        UserinfoExample userinfoExample = new UserinfoExample();
        UserinfoExample.Criteria criteria = userinfoExample.createCriteria();
        criteria.andUsernameEqualTo(userinfo.getUsername());
        criteria.andPwdEqualTo(userinfo.getPwd());
        return userinfoMapper.selectByExample(userinfoExample);
    }

    /**
     * 重设密码 - 后台管理员
     */
    public int updatePwd(Integer id,String pwd){
        Userinfo userinfo = new Userinfo();
        userinfo.setUserid(id);
        userinfo.setPwd(pwd);
        int result = userinfoMapper.updateByPrimaryKeySelective(userinfo);
        return result;
    }


    /**
     * 根据用户id获取所有的权限
     * @param uid
     * @return
     */
    public List getPermissionByUid(Integer uid){
        String getPermissionByuid = " SELECT distinct REPLACE(m.path,'.','') AS path FROM auth_module m "
                +" LEFT JOIN auth_modulejoinrole mr ON m.moduleId = mr.moduleId "
                +" LEFT JOIN auth_role r ON mr.roleId = r.id "
                +" LEFT JOIN auth_userjoinrole ur ON r.id = ur.roleId "
                +" LEFT JOIN userinfo u ON ur.userId = u.userId "
                +" WHERE u.userId = ?";
        List<String> pathlist = dao.queryForObjectList(getPermissionByuid,new Object[]{uid},String.class);
        return pathlist;
    }

    /**
     * 根据uid获取模板树
     * @return
     */
    public List getModuleTreeByUID(Integer uid){
        String getPermissionByuid = " SELECT distinct m.* FROM auth_module m  "
                +" LEFT JOIN auth_modulejoinrole mr ON m.moduleId = mr.moduleId "
                +" LEFT JOIN auth_role r ON mr.roleId = r.id "
                +" LEFT JOIN auth_userjoinrole ur ON r.id = ur.roleId "
                +" LEFT JOIN userinfo u ON ur.userId = u.userId "
                +" WHERE u.userId = ? OR (m.parentId = 0 AND m.path = '') "
                +" ORDER BY ordernumber ";
        List<Module> result = new ArrayList<Module>();
        List<Module> modulelist = dao.queryForObjectList(getPermissionByuid,new Object[]{uid},Module.class);
        if (modulelist != null) {
            for (int i = 0; i < modulelist.size(); i++) {
                Module m = modulelist.get(i);
                if (m.getParentId() == 0){  //如果是根结点
                    resetModules(m,modulelist);  //给m添加子结点
                    result.add(m);
                }
            }
        }
        return result;
    }

    /**
     * 为m设置子节点
     * @param m  父节点
     * @param ms 在ms中找到m的子节点
     */
    private void resetModules(Module m, List<Module> ms){
        for(int i=0;i<ms.size();i++){   //循环modules
            Module c=ms.get(i);    //获取module
            //if (c.getParentId()!=null) {  //当module的父code不为空时,即是子菜单
                if(c.getParentId() == m.getModuleId()){   //如果module的的父code等于m的code,也就是遍历到的module是m的子菜单
                    m.getChildren().add(c);   //直接加入到m
                    resetModules(c,ms);    //把这个二级菜单c加入到m中
                }
            //}
        }
    }
}