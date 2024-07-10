/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-本地库
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : yf_exam_lite

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 20/02/2023 10:38:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for el_exam
-- ----------------------------
DROP TABLE IF EXISTS `el_exam`;
CREATE TABLE `el_exam` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '考试名称',
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '考试描述',
  `open_type` int NOT NULL DEFAULT '1' COMMENT '1公开2部门3定员',
  `state` int NOT NULL DEFAULT '0' COMMENT '考试状态',
  `time_limit` tinyint NOT NULL DEFAULT '0' COMMENT '是否限时',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `total_score` int NOT NULL DEFAULT '0' COMMENT '总分数',
  `total_time` int NOT NULL DEFAULT '0' COMMENT '总时长（分钟）',
  `qualify_score` int NOT NULL DEFAULT '0' COMMENT '及格分数',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `open_type` (`open_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试主表';

-- ----------------------------
-- Records of el_exam
-- ----------------------------
BEGIN;
INSERT INTO `el_exam` (`id`, `title`, `content`, `open_type`, `state`, `time_limit`, `start_time`, `end_time`, `create_time`, `update_time`, `total_score`, `total_time`, `qualify_score`) VALUES ('1587621704140427265', '【云帆演示】考试', '【云帆演示】考试', 1, 0, 0, NULL, NULL, '2022-11-02 09:44:46', '2022-11-02 09:44:46', 100, 30, 60);
COMMIT;

-- ----------------------------
-- Table structure for el_exam_depart
-- ----------------------------
DROP TABLE IF EXISTS `el_exam_depart`;
CREATE TABLE `el_exam_depart` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'ID',
  `exam_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '考试ID',
  `depart_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `exam_id` (`exam_id`),
  KEY `depart_id` (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试部门';

-- ----------------------------
-- Records of el_exam_depart
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for el_exam_repo
-- ----------------------------
DROP TABLE IF EXISTS `el_exam_repo`;
CREATE TABLE `el_exam_repo` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `exam_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '考试ID',
  `repo_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '题库ID',
  `radio_count` int NOT NULL DEFAULT '0' COMMENT '单选题数量',
  `radio_score` int NOT NULL DEFAULT '0' COMMENT '单选题分数',
  `multi_count` int NOT NULL DEFAULT '0' COMMENT '多选题数量',
  `multi_score` int NOT NULL DEFAULT '0' COMMENT '多选题分数',
  `judge_count` int NOT NULL DEFAULT '0' COMMENT '判断题数量',
  `judge_score` int NOT NULL DEFAULT '0' COMMENT '判断题分数',
  `saq_count` int NOT NULL DEFAULT '0' COMMENT '简答题数量',
  `saq_score` int NOT NULL DEFAULT '0' COMMENT '简答题分数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `exam_repo_id` (`exam_id`,`repo_id`),
  KEY `rule_id` (`exam_id`) USING BTREE,
  KEY `repo_id` (`repo_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试题库';

-- ----------------------------
-- Records of el_exam_repo
-- ----------------------------
BEGIN;
INSERT INTO `el_exam_repo` (`id`, `exam_id`, `repo_id`, `radio_count`, `radio_score`, `multi_count`, `multi_score`, `judge_count`, `judge_score`, `saq_count`, `saq_score`) VALUES ('1627496519370297345', '1587621704140427265', '1587622451624120321', 2, 10, 6, 10, 2, 10, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for el_paper
-- ----------------------------
DROP TABLE IF EXISTS `el_paper`;
CREATE TABLE `el_paper` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '试卷ID',
  `user_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `depart_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门ID',
  `exam_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则ID',
  `title` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '考试标题',
  `total_time` int NOT NULL DEFAULT '0' COMMENT '考试时长',
  `user_time` int NOT NULL DEFAULT '0' COMMENT '用户时长',
  `total_score` int NOT NULL DEFAULT '0' COMMENT '试卷总分',
  `qualify_score` int NOT NULL DEFAULT '0' COMMENT '及格分',
  `obj_score` int NOT NULL DEFAULT '0' COMMENT '客观分',
  `subj_score` int NOT NULL DEFAULT '0' COMMENT '主观分',
  `user_score` int NOT NULL COMMENT '用户得分',
  `has_saq` tinyint NOT NULL DEFAULT '0' COMMENT '是否包含简答题',
  `state` int NOT NULL DEFAULT '1' COMMENT '试卷状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `limit_time` datetime DEFAULT NULL COMMENT '截止时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `exam_id` (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试记录';

-- ----------------------------
-- Records of el_paper
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for el_paper_qu
-- ----------------------------
DROP TABLE IF EXISTS `el_paper_qu`;
CREATE TABLE `el_paper_qu` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `paper_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '试卷ID',
  `qu_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目ID',
  `qu_type` int NOT NULL COMMENT '题目类型',
  `answered` tinyint NOT NULL DEFAULT '0' COMMENT '是否已答',
  `answer` varchar(5000) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主观答案',
  `sort` int NOT NULL DEFAULT '0' COMMENT '问题排序',
  `score` int NOT NULL DEFAULT '0' COMMENT '单题分分值',
  `actual_score` int NOT NULL DEFAULT '0' COMMENT '实际得分(主观题)',
  `is_right` tinyint NOT NULL DEFAULT '0' COMMENT '是否答对',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `paper_id` (`paper_id`) USING BTREE,
  KEY `qu_id` (`qu_id`) USING BTREE,
  KEY `paper_qu_id` (`paper_id`,`qu_id`) USING BTREE,
  KEY `sort` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试记录考题';

-- ----------------------------
-- Records of el_paper_qu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for el_paper_qu_answer
-- ----------------------------
DROP TABLE IF EXISTS `el_paper_qu_answer`;
CREATE TABLE `el_paper_qu_answer` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '自增ID',
  `paper_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '试卷ID',
  `answer_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '回答项ID',
  `qu_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目ID',
  `is_right` tinyint NOT NULL DEFAULT '0' COMMENT '是否正确项',
  `checked` tinyint NOT NULL DEFAULT '0' COMMENT '是否选中',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `abc` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '选项标签',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `paper_id` (`paper_id`) USING BTREE,
  KEY `qu_id` (`qu_id`) USING BTREE,
  KEY `paper_qu_id` (`paper_id`,`qu_id`) USING BTREE,
  KEY `sort` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试记录答案';

-- ----------------------------
-- Records of el_paper_qu_answer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for el_qu
-- ----------------------------
DROP TABLE IF EXISTS `el_qu`;
CREATE TABLE `el_qu` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目ID',
  `qu_type` int NOT NULL COMMENT '题目类型',
  `level` int NOT NULL DEFAULT '1' COMMENT '1普通,2较难',
  `image` varchar(500) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '题目图片',
  `content` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '题目备注',
  `analysis` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '整题解析',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `qu_type` (`qu_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='试题主表';

-- ----------------------------
-- Records of el_qu
-- ----------------------------
BEGIN;
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642263625729', 1, 1, '', '五个答案中哪个是最好的类比？女儿对于父亲相当于侄女对于', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642393649153', 1, 1, '', '五个答案中哪个是最好的类比？皮对于树相当于鳞对于', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642473340930', 1, 1, '', '火车守车(车尾)长6.4米。机车的长度等于守车的长加上半节车厢的长。车厢长度等于守车长加上机车长。火车的机车、车厢、守车共长多少米？', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642557227009', 1, 1, '', '角对于元相当于小时对于', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642641113089', 1, 1, '', '如果把这个大立方体的六个面全部涂上黑色，然后按图中虚线把它切成36个小方块，两面有黑色的小方块有多少个？', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642699833345', 1, 1, '', '找出与众不同的一个：', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642762747906', 2, 1, '', '以下哪些是中国的特别行政区？', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642838245378', 2, 1, '', '中国东北三省是指（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642909548545', 3, 1, '', '咖啡的故乡是非洲吗？', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622642976657410', 3, 1, '', '世界上最长的山脉安第斯山脉', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643039571970', 1, 1, '', '西亚波斯湾沿岸比较富裕，其原因是（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '西亚波斯湾沿岸国家比较富裕，是因为大量出口石油资源．');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643102486530', 1, 1, '', '我国最北面的城市是哪个（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '漠河市，隶属黑龙江省大兴安岭地区。 [1] 地处黑龙江省北部。西与内蒙古自治区呼伦贝尔市额尔古纳市为邻，南与内蒙古自治区根河市和呼中区交界，东与塔河县接壤，北隔黑龙江与俄罗斯外贝加尔边疆区（原赤塔州）和阿穆尔州相望，是中国最北端的县级行政区；地势南高北低，南北呈坡降趋势，属于寒温带大陆性季风气候。下辖6个镇，总面积18427平方千米。 [2] 根据第七次人口普查数据，截至2020年11月1日零时，漠河市常住人口为54036人。');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643173789698', 1, 1, '', '人们把社会生产的各个部门划分为三类产业，下列属于第三产业的是（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '通常人们把生产的各部门划分为三类产业．农业是第一产业，包括种植业、林业、畜牧业、渔业等；工业和建筑业是第二产业；流通部门和服务部门是第三产业');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643240898561', 1, 1, '', '亚洲人中分布不是很均匀，其中人中较稀疏的部分是（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '人口稠密地区都处在中低纬度、近海、平原地区，亚洲人口稀疏的地区在纬度位置较高的北亚，沙漠广布的西亚及气候干旱的中亚地区．');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643312201730', 1, 1, '', '亚洲各国经济发展不平衡，下列国家中人均国民生产总值最高的是（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '本题还可以考查人口：人口超亿的国家有中国、印度、印度尼西亚、巴基斯坦、日本、孟加拉国等6个；各大洲人口排序：亚非欧南北美大洋洲；各大洲人口增长率：非南美亚大洋北美欧；著名的民族文化：亚洲三个人类文明发祥地：华夏文化(黄河——长江中下游地区)、印度河流域文化、两河流域文化(由幼法拉底河和底格里斯河冲积而成的美索不达米亚平原地区)；还有恒河文化、阿拉伯文化等。亚洲只有日本属于发达国家。');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643396087810', 1, 1, '', '地球上0度经线和0度纬线的长度相比（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '0度经线和0度纬线相比()0度纬线的长度是0度经线的2倍。 因为经线是半圆，赤道是大圆。');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643467390977', 1, 1, '', '下列国家人口超过1亿的南亚国家是（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '世界人口超过一亿的国家，从多到少依次为：中国、印度、美国、印度尼西亚、巴西、巴基斯坦、俄罗斯、孟加拉国、尼日利亚、日本和墨西哥共有11个．其中属于南亚的是印度、巴基斯坦和孟加拉国．');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643551277058', 1, 1, '', '世界上跨东西、南北距离最长的大洲是（）', '2022-11-02 09:48:29', '2022-11-02 09:48:29', '', '在全球的七大洲中，亚洲是世界上面积最大的一洲，也是南北跨纬度最多，热量差异大； 东西跨经度仅次于南极洲，东西距离最长的大洲；大洋洲是世界上最小的一洲 考点：本题主要考查大洲和大洋。');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643626774530', 1, 1, '', '地球公转会产生（）', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '地球自转产生的现象有： 1、昼夜更替现象，向着太阳的半球,是白天,背着太阳的半球,是黑夜； 2、南北半球的地转偏向力引起的各种运动旋转现象； 3、地球上不同经度的地方,有不同的地方时；经度每隔15度,地方时相差一小时； 4、东西部地区的时间差现象，生物作息规律现象； 5、对地球形状的影响.地球自转所产生的惯性离心力,使得地球由两级向赤道逐渐膨胀,成为目前略扁的旋转椭球体。 地球公转产生的现象有： 1、根据太阳高度的差异,划分出五带：北寒、北温、热带、南温、南寒； 2、根据获得热量多少的时间差异,划分出四季：春、夏、秋、冬； 3、昼夜长短的变化现象； 4、天象位置的变化；生物生长规律现象. 5、正午太阳高度的变化；夏至日太阳直射北回归线，全球正午太阳高度从北回归线向南北两侧逐渐递减；二分日太阳直射赤道，全球正午太阳高度从赤道向两极递减，全球昼夜平分；冬至日太阳直射南回归线，全球正午高度从南回归线向南北两侧逐渐递减，南回归线及其以南的地区正午太阳高度达到最大值，北半球各纬度正午太阳高度达到最小值。');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643693883394', 2, 1, '', '南极旅游的兴起，请下列哪些因素有关（）', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643760992258', 2, 1, '', '交通运输线路（公路、铁路），选址的原因有（）', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643823906818', 2, 1, 'http://localhost:8201/upload/file/2022/11/07/1589528183307354113.jpg', '地球自转产生的现象有（）', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643895209985', 2, 1, '', '地球公转产生的现象有（）', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622643979096066', 3, 1, '', '地球上出现的潮汐是由于地月吸引力。', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622644054593538', 3, 1, '', '被称为“万园之园”的我国古典园林是颐和园。', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622644134285314', 3, 1, '', '世界最长的山脉是安第斯山脉。', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622644201394178', 3, 1, '', '仪表显示当前发动机转速是6000转克/分钟。', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '仪表显示当前车速是20公里/小时。');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622644281085953', 3, 1, '', '仪表显示当前冷却液的温度是90°C。', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '水温表：指示发动机冷却液的温度，单位为摄氏度(C)。水温表指针所指的位置显示当前冷却液的温度。\n如图所示，指针指的位置是90，表示当前冷却液的温度是9o°C。');
INSERT INTO `el_qu` (`id`, `qu_type`, `level`, `image`, `content`, `create_time`, `update_time`, `remark`, `analysis`) VALUES ('1587622644356583425', 1, 1, '', '我国面积最大的湖泊是（）。', '2022-11-02 09:48:30', '2022-11-02 09:48:30', '', '青海湖是我国最大的湖泊，它是一个咸水湖，面积约4450平方公里，说起来也不小了，但是如果把它放到全世界范围来看的话，青海湖实际上并非大型湖泊，单就面积而言，在全世界排名第34位，和我国国土面积世界第三的位置很不相称，世界最大湖泊里海的面积（38万平方公里），就相当于84个青海湖。');
COMMIT;

-- ----------------------------
-- Table structure for el_qu_answer
-- ----------------------------
DROP TABLE IF EXISTS `el_qu_answer`;
CREATE TABLE `el_qu_answer` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '答案ID',
  `qu_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题ID',
  `is_right` tinyint NOT NULL DEFAULT '0' COMMENT '是否正确',
  `image` varchar(500) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '选项图片',
  `content` varchar(5000) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '答案内容',
  `analysis` varchar(5000) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '答案分析',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `qu_id` (`qu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='试题答案选项';

-- ----------------------------
-- Records of el_qu_answer
-- ----------------------------
BEGIN;
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642309763073', '1587622642263625729', 0, '', '母亲', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642318151681', '1587622642263625729', 0, '', '哥哥', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642318151682', '1587622642263625729', 0, '', '侄子', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642318151683', '1587622642263625729', 0, '', '表兄', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642318151684', '1587622642263625729', 1, '', '叔叔', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642402037761', '1587622642393649153', 0, '', '鳃', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642402037762', '1587622642393649153', 0, '', '大海', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642402037763', '1587622642393649153', 0, '', '渔夫', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642402037764', '1587622642393649153', 1, '', '鱼', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642402037765', '1587622642393649153', 0, '', '鳍', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642490118145', '1587622642473340930', 0, '', '25.6米', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642490118146', '1587622642473340930', 0, '', '36米', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642490118147', '1587622642473340930', 1, '', '51.2米', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642490118148', '1587622642473340930', 0, '', '64.4米', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642490118149', '1587622642473340930', 0, '', '76.2米', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642569809922', '1587622642557227009', 1, '', '分', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642569809923', '1587622642557227009', 0, '', '秒', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642569809924', '1587622642557227009', 0, '', '月', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642569809925', '1587622642557227009', 0, '', '日', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642569809926', '1587622642557227009', 0, '', '钟', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642649501697', '1587622642641113089', 0, '', '10', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642649501698', '1587622642641113089', 0, '', '12', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642649501699', '1587622642641113089', 1, '', '16', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642649501700', '1587622642641113089', 0, '', '20', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642649501701', '1587622642641113089', 0, '', '8', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642708221953', '1587622642699833345', 0, '', '西安', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642708221954', '1587622642699833345', 0, '', '郑州', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642708221955', '1587622642699833345', 1, '', '哈尔滨', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642708221956', '1587622642699833345', 0, '', '昆明', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642708221957', '1587622642699833345', 0, '', '南昌', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642771136513', '1587622642762747906', 1, '', '香港', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642775330818', '1587622642762747906', 1, '', '澳门', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642775330819', '1587622642762747906', 0, '', '珠海', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642775330820', '1587622642762747906', 0, '', '重庆', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642846633985', '1587622642838245378', 1, '', '黑龙江省', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642846633986', '1587622642838245378', 1, '', '吉林省', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642846633987', '1587622642838245378', 1, '', '辽宁省', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642846633988', '1587622642838245378', 0, '', '河北省', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642917937153', '1587622642909548545', 1, '', '正确', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642917937154', '1587622642909548545', 0, '', '错误', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642985046018', '1587622642976657410', 1, '', '正确', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622642989240321', '1587622642976657410', 0, '', '错误', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643047960578', '1587622643039571970', 0, '', '工亚基础雄厚', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643047960579', '1587622643039571970', 1, '', '拥有丰富的石油资源', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643047960580', '1587622643039571970', 0, '', '第三产业在国内生产总值中的比重大', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643052154882', '1587622643039571970', 0, '', '大力发展出口加工工业', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643110875137', '1587622643102486530', 0, '', '铁岭', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643110875138', '1587622643102486530', 0, '', '齐齐哈尔', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643110875139', '1587622643102486530', 0, '', '沈阳', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643110875140', '1587622643102486530', 1, '', '漠河', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643182178305', '1587622643173789698', 0, '', '工业', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643182178306', '1587622643173789698', 0, '', '建筑业', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643182178307', '1587622643173789698', 1, '', '服务业', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643182178308', '1587622643173789698', 0, '', '农业', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643249287170', '1587622643240898561', 0, '', '东亚', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643253481474', '1587622643240898561', 0, '', '东南亚', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643253481475', '1587622643240898561', 0, '', '南亚', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643253481476', '1587622643240898561', 1, '', '西亚', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643324784642', '1587622643312201730', 0, '', '韩国', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643324784643', '1587622643312201730', 0, '', '新加坡', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643324784644', '1587622643312201730', 1, '', '日本', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643324784645', '1587622643312201730', 0, '', '马来西亚', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643404476417', '1587622643396087810', 1, '', '0度纬线稍长', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643404476418', '1587622643396087810', 0, '', '0度经线稍长', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643404476419', '1587622643396087810', 0, '', '相等', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643404476420', '1587622643396087810', 0, '', '0度经线不到0度纬线的1/2', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643479973889', '1587622643467390977', 1, '', '孟加拉国', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643479973890', '1587622643467390977', 0, '', '印度尼西亚', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643479973891', '1587622643467390977', 0, '', '日本', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643479973892', '1587622643467390977', 0, '', '韩国', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643563859970', '1587622643551277058', 0, '', '非洲', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643563859971', '1587622643551277058', 0, '', '北美洲', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643563859972', '1587622643551277058', 0, '', '大洋洲', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643563859973', '1587622643551277058', 1, '', '亚洲', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643635163138', '1587622643626774530', 1, '', '四季现象', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643639357441', '1587622643626774530', 0, '', '昼夜交替', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643639357442', '1587622643626774530', 0, '', '时间差异', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643639357443', '1587622643626774530', 0, '', '日期差异', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643702272001', '1587622643693883394', 1, '', '人们的求知、探秘和猎奇欲望的增长', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643702272002', '1587622643693883394', 1, '', '交通工具的发展', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643702272003', '1587622643693883394', 0, '', '促使不同国家的地区的文化交增进友谊', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643702272004', '1587622643693883394', 1, '', '科学技术的进步', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643765186561', '1587622643760992258', 1, '', '等高线稀疏', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643769380865', '1587622643760992258', 1, '', '地形坡度和缓', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643769380866', '1587622643760992258', 0, '', '隧道多', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643769380867', '1587622643760992258', 0, '', '地形复杂', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643832295426', '1587622643823906818', 1, 'http://localhost:8201/upload/file/2022/11/07/1589528211451133954.jpg', '南北半球的地转偏向力引起的各种运动旋转现象；', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643832295427', '1587622643823906818', 1, '', '地球上不同经度的地方,有不同的地方时；经度每隔15度,地方时相差一小时；', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643832295428', '1587622643823906818', 1, 'http://localhost:8201/upload/file/2022/11/07/1589528315486650369.jpeg', '东西部地区的时间差现象，生物作息规律现象；', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643832295429', '1587622643823906818', 1, '', '对地球形状的影响.地球自转所产生的惯性离心力,使得地球由两级向赤道逐渐膨胀,成为目前略扁的旋转椭球体。', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643832295430', '1587622643823906818', 1, '', '昼夜更替现象，向着太阳的半球,是白天,背着太阳的半球,是黑夜；', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643903598594', '1587622643895209985', 1, '', '根据太阳高度的差异,划分出五带：北寒、北温、热带、南温、南寒；', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643903598595', '1587622643895209985', 1, '', '根据获得热量多少的时间差异,划分出四季：春、夏、秋、冬；', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643903598596', '1587622643895209985', 1, '', '昼夜长短的变化现象；', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643903598597', '1587622643895209985', 1, '', '正午太阳高度的变化；夏至日太阳直射北回归线，全球正午太阳高度从北回归线向南北两侧逐渐递减；二分日太阳直射赤道，全球正午太阳高度从赤道向两极递减，全球昼夜平分；冬至日太阳直射南回归线，全球正午高度从南回归线向南北两侧逐渐递减，南回归线及其以南的地区正午太阳高度达到最大值，北半球各纬度正午太阳高度达到最小值。', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643983290369', '1587622643979096066', 1, '', '正确', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622643987484674', '1587622643979096066', 0, '', '错误', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644058787842', '1587622644054593538', 0, '', '正确', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644062982146', '1587622644054593538', 1, '', '错误', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644138479618', '1587622644134285314', 1, '', '正确', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644142673922', '1587622644134285314', 0, '', '错误', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644209782786', '1587622644201394178', 0, '', '正确', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644209782787', '1587622644201394178', 1, '', '错误', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644289474562', '1587622644281085953', 1, '', '正确', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644289474563', '1587622644281085953', 0, '', '错误', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644364972034', '1587622644356583425', 1, '', '青海湖', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644364972035', '1587622644356583425', 0, '', '太湖', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644364972036', '1587622644356583425', 0, '', '洞庭湖', '');
INSERT INTO `el_qu_answer` (`id`, `qu_id`, `is_right`, `image`, `content`, `analysis`) VALUES ('1587622644369166338', '1587622644356583425', 0, '', '鄱阳湖', '');
COMMIT;

-- ----------------------------
-- Table structure for el_qu_repo
-- ----------------------------
DROP TABLE IF EXISTS `el_qu_repo`;
CREATE TABLE `el_qu_repo` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `qu_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '试题',
  `repo_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '归属题库',
  `qu_type` int NOT NULL DEFAULT '0' COMMENT '题目类型',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `qu_id` (`qu_id`) USING BTREE,
  KEY `repo_id` (`repo_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='试题题库关联';

-- ----------------------------
-- Records of el_qu_repo
-- ----------------------------
BEGIN;
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670122192897', '1587622643760992258', '1587622451624120321', 2, 1);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670143164418', '1587622644281085953', '1587622451624120321', 3, 2);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670159941633', '1587622643626774530', '1587622451624120321', 1, 3);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670180913153', '1587622644134285314', '1587622451624120321', 3, 4);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670201884673', '1587622643979096066', '1587622451624120321', 3, 5);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670243827714', '1587622644356583425', '1587622451624120321', 1, 6);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670268993537', '1587622643693883394', '1587622451624120321', 2, 7);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670315130881', '1587622644201394178', '1587622451624120321', 3, 8);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670340296706', '1587622644054593538', '1587622451624120321', 3, 9);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670361268226', '1587622643895209985', '1587622451624120321', 2, 10);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670382239745', '1587622642641113089', '1587622451624120321', 1, 11);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670411599873', '1587622643102486530', '1587622451624120321', 1, 12);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670436765698', '1587622642473340930', '1587622451624120321', 1, 13);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670461931522', '1587622642976657410', '1587622451624120321', 3, 14);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670491291650', '1587622642263625729', '1587622451624120321', 1, 15);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670520651778', '1587622643467390977', '1587622451624120321', 1, 16);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670545817602', '1587622642838245378', '1587622451624120321', 2, 17);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670575177730', '1587622643312201730', '1587622451624120321', 1, 18);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670604537858', '1587622642699833345', '1587622451624120321', 1, 19);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670638092289', '1587622643173789698', '1587622451624120321', 1, 20);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670667452417', '1587622642557227009', '1587622451624120321', 1, 21);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670696812545', '1587622643039571970', '1587622451624120321', 1, 22);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670721978369', '1587622643551277058', '1587622451624120321', 1, 23);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670751338497', '1587622642393649153', '1587622451624120321', 1, 24);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670776504322', '1587622642909548545', '1587622451624120321', 3, 25);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670810058754', '1587622643396087810', '1587622451624120321', 1, 26);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670843613186', '1587622642762747906', '1587622451624120321', 2, 27);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1587622670872973313', '1587622643240898561', '1587622451624120321', 1, 28);
INSERT INTO `el_qu_repo` (`id`, `qu_id`, `repo_id`, `qu_type`, `sort`) VALUES ('1589528323552296962', '1587622643823906818', '1587622451624120321', 2, 29);
COMMIT;

-- ----------------------------
-- Table structure for el_repo
-- ----------------------------
DROP TABLE IF EXISTS `el_repo`;
CREATE TABLE `el_repo` (
  `id` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '题库ID',
  `code` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '题库编号',
  `title` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '题库名称',
  `remark` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '题库备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题库信息';

-- ----------------------------
-- Records of el_repo
-- ----------------------------
BEGIN;
INSERT INTO `el_repo` (`id`, `code`, `title`, `remark`, `create_time`, `update_time`) VALUES ('1587622451624120321', '', '【云帆】演示题库', '【云帆】演示题库', '2022-11-02 09:47:44', '2022-11-02 09:47:44');
COMMIT;

-- ----------------------------
-- Table structure for el_user_book
-- ----------------------------
DROP TABLE IF EXISTS `el_user_book`;
CREATE TABLE `el_user_book` (
  `id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `exam_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '考试ID',
  `user_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `qu_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目ID',
  `create_time` datetime DEFAULT NULL COMMENT '加入时间',
  `update_time` datetime DEFAULT NULL COMMENT '最近错误时间',
  `wrong_count` int NOT NULL COMMENT '错误时间',
  `title` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目标题',
  `sort` int NOT NULL COMMENT '错题序号',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `sort` (`sort`),
  KEY `exam_id` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='错题本';

-- ----------------------------
-- Records of el_user_book
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for el_user_exam
-- ----------------------------
DROP TABLE IF EXISTS `el_user_exam`;
CREATE TABLE `el_user_exam` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `exam_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '考试ID',
  `try_count` int NOT NULL DEFAULT '1' COMMENT '考试次数',
  `max_score` int NOT NULL DEFAULT '0' COMMENT '最高分数',
  `passed` tinyint NOT NULL DEFAULT '0' COMMENT '是否通过',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`,`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考试记录';

-- ----------------------------
-- Records of el_user_exam
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_SCHEDULER_STATE` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES ('examScheduler', 'MacBook-Pro-16.local1676860344454', 1676860726631, 10000);
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `INT_PROP_1` int DEFAULT NULL,
  `INT_PROP_2` int DEFAULT NULL,
  `LONG_PROP_1` bigint DEFAULT NULL,
  `LONG_PROP_2` bigint DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint DEFAULT NULL,
  `PREV_FIRE_TIME` bigint DEFAULT NULL,
  `PRIORITY` int DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `site_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统名称',
  `front_logo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '前端LOGO',
  `back_logo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '后台LOGO',
  `copy_right` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版权信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  `update_by` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '修改人',
  `data_flag` int DEFAULT '0' COMMENT '数据标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统设置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` (`id`, `site_name`, `front_logo`, `back_logo`, `copy_right`, `create_time`, `update_time`, `create_by`, `update_by`, `data_flag`) VALUES ('1', '云帆在线培训考试系统', NULL, NULL, NULL, '2020-12-03 16:51:30', '2020-12-03 16:51:30', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart`;
CREATE TABLE `sys_depart` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `dept_type` int NOT NULL DEFAULT '1' COMMENT '1公司2部门',
  `parent_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属上级',
  `dept_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `dept_code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门编码',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门信息';

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
BEGIN;
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1302853644578000898', 1, '0', '北京云帆互联科技有限公司', 'A01', 1);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1302855940200284161', 1, '1302855776496599041', '后端组', 'A01A01A01', 2);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1302855994843676674', 1, '1302855776496599041', '前端组', 'A01A01A02', 1);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1302856017283203073', 1, '1302855776496599041', '产品组', 'A01A01A03', 3);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1302856084475953154', 1, '1302855776496599041', '测试组', 'A01A01A05', 5);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1302856266567467010', 1, '1302855896415944705', '客户一组', 'A01A05A01', 1);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1302856320602685442', 1, '1302855896415944705', '客服二组', 'A01A05A02', 2);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1318103313740320770', 1, '1302853644578000898', '技术部', 'A01A01', 1);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1318103339229106178', 1, '1302853644578000898', '人事部', 'A01A02', 2);
INSERT INTO `sys_depart` (`id`, `dept_type`, `parent_id`, `dept_name`, `dept_code`, `sort`) VALUES ('1318103362494910465', 1, '1302853644578000898', '财务部', 'A01A03', 3);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `role_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `role_name`) VALUES ('sa', '超级管理员');
INSERT INTO `sys_role` (`id`, `role_name`) VALUES ('student', '学员');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `real_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '真实姓名',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码盐',
  `role_ids` varchar(500) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色列表',
  `depart_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int NOT NULL DEFAULT '0' COMMENT '状态',
  `data_flag` int NOT NULL DEFAULT '0' COMMENT '0正常,1隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `user_name`, `real_name`, `password`, `salt`, `role_ids`, `depart_id`, `create_time`, `update_time`, `state`, `data_flag`) VALUES ('10001', 'admin', '超管A', '06681cd08837b21adf6b5ef9279d403d', 'XoFFuS', 'sa', '1318103313740320770', '2020-04-20 13:51:03', '2020-04-20 13:51:03', 0, 0);
INSERT INTO `sys_user` (`id`, `user_name`, `real_name`, `password`, `salt`, `role_ids`, `depart_id`, `create_time`, `update_time`, `state`, `data_flag`) VALUES ('1252125239901696002', 'person', '张三', '6dfdd6761a3e8319719f32abb9aeae9c', 'tZCjLq', 'student', '1318103339229106178', '2020-04-20 14:41:35', '2020-04-20 14:41:35', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES ('1318103579445284865', '10001', 'sa');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES ('1318128865264132097', '1252125239901696002', 'student');
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES ('1587574421424279555', '1587574421424279554', 'student');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
