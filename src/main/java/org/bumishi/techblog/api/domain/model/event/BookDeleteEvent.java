package org.bumishi.techblog.api.domain.model.event;

/**
 * book删除事件，用于通知缓存更新
 *
 * @author qiang.xie
 * @date 2017/1/7
 */
public class BookDeleteEvent {

    private String bookId;

    public BookDeleteEvent(String blogId) {
        this.bookId = blogId;
    }

    public String getBookId() {
        return bookId;
    }
}
