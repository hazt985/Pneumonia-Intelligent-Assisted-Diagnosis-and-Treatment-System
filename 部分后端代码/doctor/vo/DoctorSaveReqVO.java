package cn.iocoder.yudao.module.demo.controller.admin.doctor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 医生新增/修改 Request VO")
@Data
public class DoctorSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20757")
    private Long id;

    @Schema(description = "医生名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "医生名不能为空")
    private String name;

    @Schema(description = "医生年龄", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "医生年龄不能为空")
    private Integer age;

    @Schema(description = "医生性别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "医生性别不能为空")
    private Integer gender;

    @Schema(description = "医生编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "医生编号不能为空")
    private String mis;

    @Schema(description = "医生职位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "医生职位不能为空")
    private String job;

    @Schema(description = "医生介绍", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "医生介绍不能为空")
    private String intro;

}