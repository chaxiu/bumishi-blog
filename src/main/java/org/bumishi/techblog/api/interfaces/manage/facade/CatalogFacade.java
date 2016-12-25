package org.bumishi.techblog.api.interfaces.manage.facade;


import org.bumishi.techblog.api.interfaces.manage.facade.assembler.NavigationAssembler;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationUpdateCommond;
import org.bumishi.toolbox.model.repositry.NavigationNodeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Service
public class CatalogFacade {

    @Autowired
    @Qualifier("catalogJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;

    public void add(NavigationCreateCommand createCommand){
        navigationNodeRepositry.add(NavigationAssembler.createCommendToDomain(createCommand));
    }

    public void update(String id,NavigationUpdateCommond updateCommond){
        navigationNodeRepositry.update(NavigationAssembler.updateCommendToDomain(id, updateCommond));

    }

    public void switchStatus(String id,boolean disable){
        if(disable){
            navigationNodeRepositry.disable(id);
        }else {
            navigationNodeRepositry.enable(id);
        }
    }

}