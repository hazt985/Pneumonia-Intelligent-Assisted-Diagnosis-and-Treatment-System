package cn.iocoder.yudao.module.demo.controller.admin.knowledge;

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

import cn.iocoder.yudao.module.demo.controller.admin.knowledge.vo.*;
import cn.iocoder.yudao.module.demo.dal.dataobject.knowledge.KnowledgeDO;
import cn.iocoder.yudao.module.demo.service.knowledge.KnowledgeService;

@Tag(name = "管理后台 - 小知识")
@RestController
@RequestMapping("/demo/knowledge")
@Validated
public class KnowledgeController {

    @Resource
    private KnowledgeService knowledgeService;

    @PostMapping("/create")
    @Operation(summary = "创建小知识")
    @PreAuthorize("@ss.hasPermission('demo:knowledge:create')")
    public CommonResult<Long> createKnowledge(@Valid @RequestBody KnowledgeSaveReqVO createReqVO) {
        return success(knowledgeService.createKnowledge(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新小知识")
    @PreAuthorize("@ss.hasPermission('demo:knowledge:update')")
    public CommonResult<Boolean> updateKnowledge(@Valid @RequestBody KnowledgeSaveReqVO updateReqVO) {
        knowledgeService.updateKnowledge(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除小知识")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('demo:knowledge:delete')")
    public CommonResult<Boolean> deleteKnowledge(@RequestParam("id") Long id) {
        knowledgeService.deleteKnowledge(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得小知识")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('demo:knowledge:query')")
    public CommonResult<KnowledgeRespVO> getKnowledge(@RequestParam("id") Long id) {
        KnowledgeDO knowledge = knowledgeService.getKnowledge(id);
        return success(BeanUtils.toBean(knowledge, KnowledgeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得小知识分页")
    @PreAuthorize("@ss.hasPermission('demo:knowledge:query')")
    public CommonResult<PageResult<KnowledgeRespVO>> getKnowledgePage(@Valid KnowledgePageReqVO pageReqVO) {
        PageResult<KnowledgeDO> pageResult = knowledgeService.getKnowledgePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KnowledgeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出小知识 Excel")
    @PreAuthorize("@ss.hasPermission('demo:knowledge:export')")
    @OperateLog(type = EXPORT)
    public void exportKnowledgeExcel(@Valid KnowledgePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<KnowledgeDO> list = knowledgeService.getKnowledgePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "小知识.xls", "数据", KnowledgeRespVO.class,
                        BeanUtils.toBean(list, KnowledgeRespVO.class));
    }

}