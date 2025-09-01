package cn.iocoder.yudao.module.demo.controller.admin.doctor;

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

import cn.iocoder.yudao.module.demo.controller.admin.doctor.vo.*;
import cn.iocoder.yudao.module.demo.dal.dataobject.doctor.DoctorDO;
import cn.iocoder.yudao.module.demo.service.doctor.DoctorService;

@Tag(name = "管理后台 - 医生")
@RestController
@RequestMapping("/demo/doctor")
@Validated
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @PostMapping("/create")
    @Operation(summary = "创建医生")
    @PreAuthorize("@ss.hasPermission('demo:doctor:create')")
    public CommonResult<Long> createDoctor(@Valid @RequestBody DoctorSaveReqVO createReqVO) {
        return success(doctorService.createDoctor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新医生")
    @PreAuthorize("@ss.hasPermission('demo:doctor:update')")
    public CommonResult<Boolean> updateDoctor(@Valid @RequestBody DoctorSaveReqVO updateReqVO) {
        doctorService.updateDoctor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除医生")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('demo:doctor:delete')")
    public CommonResult<Boolean> deleteDoctor(@RequestParam("id") Long id) {
        doctorService.deleteDoctor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得医生")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('demo:doctor:query')")
    public CommonResult<DoctorRespVO> getDoctor(@RequestParam("id") Long id) {
        DoctorDO doctor = doctorService.getDoctor(id);
        return success(BeanUtils.toBean(doctor, DoctorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得医生分页")
    @PreAuthorize("@ss.hasPermission('demo:doctor:query')")
    public CommonResult<PageResult<DoctorRespVO>> getDoctorPage(@Valid DoctorPageReqVO pageReqVO) {
        PageResult<DoctorDO> pageResult = doctorService.getDoctorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DoctorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出医生 Excel")
    @PreAuthorize("@ss.hasPermission('demo:doctor:export')")
    @OperateLog(type = EXPORT)
    public void exportDoctorExcel(@Valid DoctorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DoctorDO> list = doctorService.getDoctorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "医生.xls", "数据", DoctorRespVO.class,
                        BeanUtils.toBean(list, DoctorRespVO.class));
    }

}