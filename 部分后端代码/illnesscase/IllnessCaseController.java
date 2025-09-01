package cn.iocoder.yudao.module.demo.controller.admin.illnesscase;

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

import cn.iocoder.yudao.module.demo.controller.admin.illnesscase.vo.*;
import cn.iocoder.yudao.module.demo.dal.dataobject.illnesscase.IllnessCaseDO;
import cn.iocoder.yudao.module.demo.service.illnesscase.IllnessCaseService;

@Tag(name = "管理后台 - 病例")
@RestController
@RequestMapping("/demo/illness-case")
@Validated
public class IllnessCaseController {

    @Resource
    private IllnessCaseService illnessCaseService;

    @PostMapping("/create")
    @Operation(summary = "创建病例")
    @PreAuthorize("@ss.hasPermission('demo:illness-case:create')")
    public CommonResult<Long> createIllnessCase(@Valid @RequestBody IllnessCaseSaveReqVO createReqVO) {
        return success(illnessCaseService.createIllnessCase(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新病例")
    @PreAuthorize("@ss.hasPermission('demo:illness-case:update')")
    public CommonResult<Boolean> updateIllnessCase(@Valid @RequestBody IllnessCaseSaveReqVO updateReqVO) {
        illnessCaseService.updateIllnessCase(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除病例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('demo:illness-case:delete')")
    public CommonResult<Boolean> deleteIllnessCase(@RequestParam("id") Long id) {
        illnessCaseService.deleteIllnessCase(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得病例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('demo:illness-case:query')")
    public CommonResult<IllnessCaseRespVO> getIllnessCase(@RequestParam("id") Long id) {
        IllnessCaseDO illnessCase = illnessCaseService.getIllnessCase(id);
        return success(BeanUtils.toBean(illnessCase, IllnessCaseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得病例分页")
    @PreAuthorize("@ss.hasPermission('demo:illness-case:query')")
    public CommonResult<PageResult<IllnessCaseRespVO>> getIllnessCasePage(@Valid IllnessCasePageReqVO pageReqVO) {
        PageResult<IllnessCaseDO> pageResult = illnessCaseService.getIllnessCasePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IllnessCaseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出病例 Excel")
    @PreAuthorize("@ss.hasPermission('demo:illness-case:export')")
    @OperateLog(type = EXPORT)
    public void exportIllnessCaseExcel(@Valid IllnessCasePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IllnessCaseDO> list = illnessCaseService.getIllnessCasePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "病例.xls", "数据", IllnessCaseRespVO.class,
                        BeanUtils.toBean(list, IllnessCaseRespVO.class));
    }

}