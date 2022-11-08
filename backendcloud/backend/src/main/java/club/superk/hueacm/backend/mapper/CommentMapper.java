package club.superk.hueacm.backend.mapper;

import club.superk.hueacm.backend.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select c.*, u.username, u.avatar from comment c " +
            "left join user u on c.user_id = u.id " +
            "where c.article_id = #{articleId} order by id desc")
    List<Comment> queryCommentByArticleId(@Param("articleId") Integer articleId);

}
