package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.domain.repository.BookIndexRepositry;
import org.bumishi.techblog.api.interfaces.manage.facade.BookIndexFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationUpdateCommond;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author qiang.xie
 * @date 2016/9/18
 */
@RestController
@RequestMapping("/admin/bookindex")
public class BookIndexController {

    @Autowired
    protected BookIndexFacade bookIndexFacade;

    @Autowired
    protected BookIndexRepositry bookIndexRepositry;

    @PostMapping(value = "/{bookId}/add")
    public RestResponse create(@PathVariable("bookId")String bookId,@RequestBody @Valid NavigationCreateCommand menu) {
        bookIndexFacade.add(menu,bookId);
        return RestResponse.ok();
    }


    @PostMapping(value = "/{bookId}/{id}/modify")
    public RestResponse modify(@PathVariable("bookId")String bookId,@PathVariable("id") String id,@RequestBody @Valid NavigationUpdateCommond menu) {
        bookIndexFacade.update(bookId,menu,id);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/delete")
    public RestResponse delete(@PathVariable("id") String id) {
        bookIndexRepositry.remove(id);
        return RestResponse.ok();
    }


    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
        return RestResponse.ok(bookIndexRepositry.get(id));
    }

    @GetMapping("/{bookId}")
    public RestResponse list(@PathVariable("bookId")String bookId) {
        return RestResponse.ok(bookIndexRepositry.getByBook(bookId));
    }

}