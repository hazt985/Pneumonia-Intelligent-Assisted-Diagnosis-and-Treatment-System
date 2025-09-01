package cn.iocoder.yudao.module.demo.controller.admin.illnesscase.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 病例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IllnessCaseRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10741")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("名字")
    private String name;

    @Schema(description = "医生名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("医生名")
    private String doctorName;

    @Schema(description = "医生编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("医生编码")
    private String doctorMis;

    @Schema(description = "年龄", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("年龄")
    private Integer age;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "性别", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer gender;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "诊断结果")
    @ExcelProperty("诊断结果")
    private String diagnosis;

    @Schema(description = "肺部图片")
    @ExcelProperty("肺部图片")
    private String lungImage;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}