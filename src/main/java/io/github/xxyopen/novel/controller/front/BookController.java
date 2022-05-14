package io.github.xxyopen.novel.controller.front;

import io.github.xxyopen.novel.core.common.constant.ApiRouterConsts;
import io.github.xxyopen.novel.core.common.resp.RestResp;
import io.github.xxyopen.novel.dto.resp.BookInfoRespDto;
import io.github.xxyopen.novel.dto.resp.BookRankRespDto;
import io.github.xxyopen.novel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 小说模块 API 接口
 *
 * @author xiongxiaoyang
 * @date 2022/5/14
 */
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 小说信息查询接口
     * */
    @GetMapping("{bookId}")
    public RestResp<BookInfoRespDto> getById(@PathVariable("bookId") Long bookId){
        return bookService.getById(bookId);
    }

    /**
     * 小说点击榜查询接口
     * */
    @GetMapping("visitRank")
    public RestResp<List<BookRankRespDto>> listVisitRankBooks(){
        return bookService.listVisitRankBooks();
    }

    /**
     * 小说新书榜查询接口
     * */
    @GetMapping("newestRank")
    public RestResp<List<BookRankRespDto>> listNewestRankBooks(){
        return bookService.listNewestRankBooks();
    }

    /**
     * 小说更新榜查询接口
     * */
    @GetMapping("updateRank")
    public RestResp<List<BookRankRespDto>> listUpdateRankBooks(){
        return bookService.listUpdateRankBooks();
    }


}
