package com.yf.exam.modules.qu.dto.export;

import com.yf.exam.core.utils.excel.annotation.ExcelField;
import com.yf.exam.core.utils.excel.fieldtype.ListType;
import lombok.Data;

import java.util.List;

/**
 * 用于导出的数据结构
 * @author bool
 */
@Data
public class QuExportDTO {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String qId;

    @ExcelField(title="题目序号", align=2, sort=1)
    private String no;
    @ExcelField(title="题目类型", align=2, sort=2)
    private String quType;
    @ExcelField(title="题目内容", align=2, sort=3)
    private String qContent;
    @ExcelField(title="整体解析", align=2, sort=4)
    private String qAnalysis;
    @ExcelField(title="题目图片", align=2, sort=5)
    private String qImage;
    @ExcelField(title="题目视频", align=2, sort=6)
    private String qVideo;
    @ExcelField(title="所属题库", align=2, sort=7, fieldType = ListType.class)
    private List<String> repoList;
    @ExcelField(title="是否正确项", align=2, sort=8)
    private String aIsRight;
    @ExcelField(title="选项内容", align=2, sort=9)
    private String aContent;
    @ExcelField(title="选项解析", align=2, sort=10)
    private String aAnalysis;
    @ExcelField(title="选项图片", align=2, sort=11)
    private String aImage;
}
