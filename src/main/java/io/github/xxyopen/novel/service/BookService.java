package io.github.xxyopen.novel.service;

import io.github.xxyopen.novel.core.common.resp.RestResp;
import io.github.xxyopen.novel.dto.resp.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 小说模块 服务类
 *
 * @author xiongxiaoyang
 * @date 2022/5/14
 */
public interface BookService {

    /**
     * 小说点击榜查询
     * */
    RestResp<List<BookRankRespDto>> listVisitRankBooks();

    /**
     * 小说新书榜查询
     * */
    RestResp<List<BookRankRespDto>> listNewestRankBooks();

    /**
     * 小说更新榜查询
     * */
    RestResp<List<BookRankRespDto>> listUpdateRankBooks();

    /**
     * 小说信息查询
     * */
    RestResp<BookInfoRespDto> getBookById(Long bookId);

    /**
     * 小说内容相关信息查询
     * */
    RestResp<BookContentAboutRespDto> getBookContentAbout(Long chapterId);

    /**
     * 小说最新章节相关信息查询
     * */
    RestResp<BookChapterAboutRespDto> getLastChapterAbout(Long bookId);

    /**
     * 小说推荐列表查询
     * */
    RestResp<List<BookInfoRespDto>> listRecBooks(Long bookId) throws NoSuchAlgorithmException;

    /**
     * 增加小说点击量
     * */
    RestResp<Void> addVisitCount(Long bookId);

    /**
     * 获取上一章节ID
     * */
    RestResp<Long> getPreChapterId(Long chapterId);

    /**
     * 获取下一章节ID
     * */
    RestResp<Long> nextChapterId(Long chapterId);

    /**
     * 小说章节列表查询
     * */
    RestResp<List<BookChapterRespDto>> listChapters(Long bookId);
}
