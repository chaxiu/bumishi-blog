package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.application.CatalogService;
import org.bumishi.techblog.api.interfaces.manage.facade.CatalogFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationUpdateCommond;
import org.bumishi.toolbox.model.RestResponse;
import org.bumishi.toolbox.model.repositry.NavigationNodeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author qiang.xie
 * @date 2016/9/18
 */
@RestController("adminCatalogController")
@RequestMapping("/admin/catalog")
public class CatalogController {

    @Autowired
  private CatalogFacade catalogFacade;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    @Qualifier("catalogJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;


    @PostMapping(value = "/add")
    public RestResponse create(@RequestBody @Valid NavigationCreateCommand catalog) {
        catalogFacade.add(catalog);
        return RestResponse.ok();
    }


    @PostMapping(value = "/{id}/modify")
    public RestResponse modify(@PathVariable("id") String id,@RequestBody @Valid NavigationUpdateCommond catalog) {
            catalogFacade.update(id,catalog);
            return RestResponse.ok();
    }


    @PostMapping(value = "/{id}/status")
    @ResponseBody
    public RestResponse switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
        catalogFacade.switchStatus(id,disable);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/delete")
    @ResponseBody
    public RestResponse delete(@PathVariable("id") String id) {
       navigationNodeRepositry.remove(id);
        return RestResponse.ok();
    }

    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
        return RestResponse.ok(navigationNodeRepositry.get(id));
    }

    @GetMapping
    public RestResponse list() {
        return RestResponse.ok(catalogService.listByOrder());
    }


}
