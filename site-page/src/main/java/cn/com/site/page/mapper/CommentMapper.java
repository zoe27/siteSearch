package cn.com.site.page.mapper;

import cn.com.site.page.vo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int insert(Comment record);

    List<Comment> selectAll();

    List<Comment> selectAllBySalaryId(@Param("salaryId") Integer salaryId);
}