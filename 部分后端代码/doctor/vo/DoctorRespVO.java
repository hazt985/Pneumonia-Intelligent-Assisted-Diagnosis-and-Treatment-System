package cn.iocoder.yudao.module.demo.controller.admin.doctor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 医生 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DoctorRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20757")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "医生名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("医生名")
    private String name;

    @Schema(description = "医生年龄", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("医生年龄")
    private Integer age;

    @Schema(description = "医生性别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "医生性别", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer gender;

    @Schema(description = "医生编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("医生编号")
    private String mis;

    @Schema(description = "医生职位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("医生职位")
    private String job;

    @Schema(description = "医生介绍", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("医生介绍")
    private String intro;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}