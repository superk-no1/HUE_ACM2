package club.superk.hueacm.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
    private Integer userId;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date createTime;

    private Integer pid;
    @TableField(exist = false)
    private String pName;
    @TableField(exist = false)
    private Integer pUserId;

    private Integer originId;
    private Integer articleId;

    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String avatar;
    @TableField(exist = false)
    private List<Comment> children;

}
