package club.superk.hueacm.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String author;
    private String avatar;
    private String title;
    private String content;
    private Integer type;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer commentCount;


}
