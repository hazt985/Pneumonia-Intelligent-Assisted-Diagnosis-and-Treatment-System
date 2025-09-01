package cn.iocoder.yudao.module.demo.controller.admin.illnesscase.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 病例新增/修改 Request VO")
@Data
public class IllnessCaseSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10741")
    private Long id;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "名字不能为空")
    private String name;

    @Schema(description = "医生名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "医生名不能为空")
    private String doctorName;

    @Schema(description = "医生编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "医生编码不能为空")
    private String doctorMis;

    @Schema(description = "年龄", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "性别不能为空")
    private Integer gender;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "诊断结果")
    private String diagnosis;

    @Schema(description = "肺部图片")
    private String lungImage;

}