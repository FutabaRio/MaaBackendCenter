package plus.maa.backend.repository.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import plus.maa.backend.controller.response.ArkLevelInfo;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author LoMu
 * Date  2022-12-25 17:56
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true)
@Document("maa_copilot")
public class Copilot implements Serializable {

    @Id
    //作业id
    private String id;

    //关卡名
    private String stageName;


    //上传者
    private String uploader;

    //上传者id
    @Indexed
    private String uploaderId;

    //查看次数
    private int views;

    //热度
    private int hotScore;

    //评级
    private int ratingLevel;

    //评级比率
    private double ratingRatio;

    //难度
    private int difficulty;

    //版本号(文档中说明:最低要求 maa 版本号，必选。保留字段)
    private String minimumRequired;


    //指定干员
    private List<Oerators> operator;
    //群组
    private List<Groups> groups;
    // 战斗中的操作
    private List<Action> actions;

    //描述
    private Doc doc;

    private ArkLevelInfo arkLevel;

    private Date createDate;
    private Date updateDate;


    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Oerators {
        // 干员名
        private String name;
        //技能序号。可选，默认 1
        private int sill;
        // 技能用法。可选，默认 0
        private int skillUsage;
        private Requirements requirements;


        @Data
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class Requirements {
            // 精英化等级。可选，默认为 0, 不要求精英化等级
            private int elite;
            // 干员等级。可选，默认为 0
            private int level;

            // 技能等级。可选，默认为 0
            private int skillLevel;
            // 模组编号。可选，默认为 0
            private int module;
            // 潜能要求。可选，默认为 0
            private int potentiality;

        }
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Groups {
        // 群组名
        private String name;
        private List<Oerators> operator;
    }


    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Action {
        // 操作类型，可选，默认 "Deploy"
        private String type;
        private int kills;
        private int costs;
        private int costChanges;
        //-1
        private int cooling;
        private String name;
        // 部署干员的位置。
        private Integer[] location;
        // 部署干员的干员朝向 中英文皆可
        private String direction;
        // 修改技能用法。当 type 为 "技能用法" 时必选
        private Integer skillUsage;

        private int preDelay;
        private int postDelay;
        // 保留字段，暂未实现
        private Long timeout;
        private String doc;
        private String docColor;

    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Doc {
        private String title;
        private String titleColor;
        private String details;
        private String detailsColor;

    }
}
