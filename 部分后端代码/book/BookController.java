package cn.iocoder.yudao.module.demo.controller.admin.book;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.demo.controller.admin.book.vo.*;
import cn.iocoder.yudao.module.demo.dal.dataobject.book.BookDO;
import cn.iocoder.yudao.module.demo.service.book.BookService;

@Tag(name = "管理后台 - 测试book")
@RestController
@RequestMapping("/demo/book")
@Validated
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("/create")
    @Operation(summary = "创建测试book")
    @PreAuthorize("@ss.hasPermission('demo:book:create')")
    public CommonResult<Long> createBook(@Valid @RequestBody BookSaveReqVO createReqVO) {
        return success(bookService.createBook(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新测试book")
    @PreAuthorize("@ss.hasPermission('demo:book:update')")
    public CommonResult<Boolean> updateBook(@Valid @RequestBody BookSaveReqVO updateReqVO) {
        bookService.updateBook(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除测试book")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('demo:book:delete')")
    public CommonResult<Boolean> deleteBook(@RequestParam("id") Long id) {
        bookService.deleteBook(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得测试book")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('demo:book:query')")
    public CommonResult<BookRespVO> getBook(@RequestParam("id") Long id) {
        BookDO book = bookService.getBook(id);
        return success(BeanUtils.toBean(book, BookRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得测试book分页")
    @PreAuthorize("@ss.hasPermission('demo:book:query')")
    public CommonResult<PageResult<BookRespVO>> getBookPage(@Valid BookPageReqVO pageReqVO) {
        PageResult<BookDO> pageResult = bookService.getBookPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BookRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出测试book Excel")
    @PreAuthorize("@ss.hasPermission('demo:book:export')")
    @OperateLog(type = EXPORT)
    public void exportBookExcel(@Valid BookPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BookDO> list = bookService.getBookPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "测试book.xls", "数据", BookRespVO.class,
                        BeanUtils.toBean(list, BookRespVO.class));
    }

}