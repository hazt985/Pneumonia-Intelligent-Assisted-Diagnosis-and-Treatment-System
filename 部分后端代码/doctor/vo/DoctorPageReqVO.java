package cn.iocoder.yudao.module.demo.controller.admin.doctor.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 医生分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DoctorPageReqVO extends PageParam {

    @Schema(description = "医生名", example = "赵六")
    private String name;

    @Schema(description = "医生年龄")
    private Integer age;

    @Schema(description = "医生性别")
    private Integer gender;

    @Schema(description = "医生编号")
    private String mis;

    @Schema(description = "医生职位")
    private String job;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}