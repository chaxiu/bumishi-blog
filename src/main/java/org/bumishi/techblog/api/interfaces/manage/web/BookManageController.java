package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.interfaces.manage.facade.BookManageFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.BookIndexFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookUpdateCommand;
import org.bumishi.techblog.api.interfaces.shard.BookQueryFacade;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 提供admin管理接口
 * Created by xieqiang on 2016/11/27.
 */
@RestController("adminBookController")
@RequestMapping("/admin/book")
public class BookManageController {


    @Autowired
    private BookManageFacade bookManageFacade;

    @Autowired
    private BookQueryFacade bookQueryFacade;

    @Autowired
    private BookIndexFacade bookIndexFacade;


    @PostMapping("/add")
    public RestResponse addBook(@RequestBody @Valid BookUpdateCommand blog){
        bookManageFacade.createBook(blog);
        return RestResponse.ok();
    }

    @PostMapping("/{id}/update")
    public RestResponse updateBook(@PathVariable("id")String id,@RequestBody @Valid BookUpdateCommand blog){
        bookManageFacade.updateBook(id, blog);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/delete")
    public RestResponse delete(@PathVariable("id") String id) {
        bookManageFacade.delete(id);
        return RestResponse.ok();
    }

    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
        return RestResponse.ok(bookManageFacade.getBook(id));
    }

    @GetMapping
    public RestResponse get(@RequestParam(value = "page",required = false,defaultValue = "1") int page){
        return RestResponse.ok(bookQueryFacade.pageQuery(page, 20));
    }


    @GetMapping("/{bookId}/indexs")
    public RestResponse list(@PathVariable("bookId") String bookId) {
        return RestResponse.ok(bookManageFacade.listByBookId(bookId));
    }
}
