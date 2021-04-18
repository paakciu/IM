-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2021-04-17 17:09:40
-- 服务器版本： 5.5.62-log
-- PHP 版本： 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `mybatis`
--

-- --------------------------------------------------------

--
-- 表的结构 `friend`
--

CREATE TABLE `friend` (
  `id` bigint(32) NOT NULL,
  `userid` bigint(32) NOT NULL,
  `frienduserid` bigint(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `group_info`
--

CREATE TABLE `group_info` (
  `id` bigint(32) NOT NULL COMMENT '群组信息的id',
  `userId` bigint(32) NOT NULL COMMENT '群主的id',
  `group_name` varchar(50) NOT NULL COMMENT '群的名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `group_info`
--

INSERT INTO `group_info` (`id`, `userId`, `group_name`) VALUES
(1, 21, 'qname'),
(2, 25, 'qname');

-- --------------------------------------------------------

--
-- 表的结构 `group_members`
--

CREATE TABLE `group_members` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `group_id` bigint(32) NOT NULL COMMENT '群组id',
  `group_userid` bigint(32) NOT NULL COMMENT '群成员id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `group_members`
--

INSERT INTO `group_members` (`id`, `group_id`, `group_userid`) VALUES
(1, 1, 21),
(2, 1, 25),
(3, 1, 5),
(4, 2, 21),
(5, 2, 25),
(12, 2, 8);

-- --------------------------------------------------------

--
-- 表的结构 `group_msg`
--

CREATE TABLE `group_msg` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `groupid` bigint(32) NOT NULL COMMENT '群组id',
  `fromid` bigint(32) NOT NULL COMMENT '发送者的id',
  `time` datetime NOT NULL COMMENT '发送的时间',
  `msg` mediumtext NOT NULL COMMENT '消息体',
  `type` int(11) DEFAULT NULL COMMENT '消息的类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `group_msg`
--

INSERT INTO `group_msg` (`id`, `groupid`, `fromid`, `time`, `msg`, `type`) VALUES
(1, 2, 21, '2021-04-15 14:21:11', 'nihao', NULL),
(2, 1, 25, '2021-04-15 14:21:32', 'ohuo', NULL),
(3, 2, 21, '2021-04-15 14:21:57', 'zailai', NULL),
(4, 1, 21, '2021-04-15 14:31:36', 'en?', NULL),
(5, 1, 21, '2021-04-15 14:33:46', 'kewu', NULL),
(6, 2, 21, '2021-04-15 14:34:22', 'a', NULL),
(7, 1, 21, '2021-04-15 14:34:27', 'nihao', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `group_msg_offline`
--

CREATE TABLE `group_msg_offline` (
  `id` bigint(32) NOT NULL COMMENT '离线消息号',
  `groupid` bigint(32) NOT NULL COMMENT '群组id',
  `toid` bigint(32) NOT NULL COMMENT '传递给哪个用户的',
  `type` int(11) DEFAULT NULL COMMENT '消息类型',
  `firstgroupmsgid` bigint(32) NOT NULL COMMENT '第一条群组消息id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `group_msg_offline`
--

INSERT INTO `group_msg_offline` (`id`, `groupid`, `toid`, `type`, `firstgroupmsgid`) VALUES
(2, 1, 5, NULL, 5),
(3, 2, 8, NULL, 6);

-- --------------------------------------------------------

--
-- 表的结构 `normal_msg`
--

CREATE TABLE `normal_msg` (
  `id` bigint(32) NOT NULL COMMENT '信息的id',
  `NM_fromid` bigint(32) NOT NULL COMMENT '谁发的',
  `NM_toid` bigint(32) NOT NULL COMMENT '发给谁',
  `NM_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送的时间',
  `NM_state` tinyint(4) DEFAULT '0' COMMENT '消息的发送状态 0-已发送 1-已经投妥 2-撤回 3-离线',
  `NM_type` tinyint(4) DEFAULT '0' COMMENT '记录消息的类型',
  `NM_msg` mediumtext NOT NULL COMMENT '消息体'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='普通消息表';

--
-- 转存表中的数据 `normal_msg`
--

INSERT INTO `normal_msg` (`id`, `NM_fromid`, `NM_toid`, `NM_time`, `NM_state`, `NM_type`, `NM_msg`) VALUES
(1, 123, 321, '2021-03-18 10:07:42', NULL, NULL, 'msg1'),
(2, 123, 321, '2021-03-18 10:07:43', NULL, NULL, 'msg2'),
(3, 123, 321, '2021-03-18 10:07:43', NULL, NULL, 'msg3'),
(4, 123, 321, '2021-03-18 10:07:43', NULL, NULL, 'msg4'),
(5, 123, 321, '2021-03-18 10:07:44', NULL, NULL, 'msg5'),
(6, 123, 321, '2021-03-18 10:07:44', NULL, NULL, 'msg6'),
(7, 123, 321, '2021-03-18 10:07:44', NULL, NULL, 'msg7'),
(8, 123, 321, '2021-03-18 10:07:44', NULL, NULL, 'msg8'),
(9, 123, 321, '2021-03-18 10:07:44', NULL, NULL, 'msg9'),
(10, 25, 21, '2021-03-31 07:42:32', NULL, NULL, 'nihao'),
(11, 25, 21, '2021-03-31 07:42:42', NULL, NULL, 'nihao'),
(12, 25, 21, '2021-03-31 07:45:17', NULL, NULL, 'nihao'),
(13, 21, 5, '2021-03-31 08:24:28', NULL, NULL, 'nihao'),
(14, 21, 25, '2021-03-31 08:26:22', NULL, NULL, 'nihao'),
(15, 21, 25, '2021-04-01 07:39:38', NULL, NULL, 'nihao'),
(16, 25, 21, '2021-04-04 12:56:29', NULL, NULL, 'wocaoxuanxue'),
(17, 25, 21, '2021-04-04 13:04:59', NULL, NULL, '真的是玄学。。'),
(18, 21, 25, '2021-04-04 13:08:58', NULL, NULL, '应该是我的心跳包的问题'),
(19, 25, 21, '2021-04-04 13:09:09', NULL, NULL, '怎么回事'),
(20, 21, 25, '2021-04-04 13:09:34', NULL, NULL, '啊？'),
(21, 25, 21, '2021-04-04 13:09:48', NULL, NULL, '怎么会漏包呀'),
(22, 21, 25, '2021-04-04 13:11:07', NULL, NULL, '应该是我的心跳包有问题啊啊啊啊啊啊'),
(23, 25, 21, '2021-04-04 13:11:46', NULL, NULL, '再来，错漏百出'),
(24, 25, 21, '2021-04-04 13:35:05', NULL, NULL, 'hinihaoya'),
(25, 25, 21, '2021-04-04 13:35:18', NULL, NULL, 'nihaoya'),
(26, 21, 25, '2021-04-04 13:35:35', NULL, NULL, 'a?'),
(27, 21, 25, '2021-04-04 13:42:42', NULL, NULL, 'buhuiba'),
(28, 21, 25, '2021-04-04 13:42:52', NULL, NULL, 'haishizheyangma'),
(29, 25, 21, '2021-04-04 13:43:06', NULL, NULL, 'kewu'),
(30, 21, 25, '2021-04-04 13:43:12', NULL, NULL, 'xiufulema'),
(31, 25, 21, '2021-04-04 13:43:19', NULL, NULL, 'ei'),
(32, 21, 25, '2021-04-04 13:47:49', NULL, NULL, 'nihao'),
(33, 21, 25, '2021-04-04 13:47:54', NULL, NULL, 'buhuibabuhuiba'),
(34, 21, 25, '2021-04-04 13:49:24', NULL, NULL, 'buhuibbbb'),
(35, 21, 25, '2021-04-04 13:49:46', NULL, NULL, 'a?'),
(36, 25, 21, '2021-04-04 13:49:54', NULL, NULL, 's'),
(37, 21, 25, '2021-04-04 13:50:48', NULL, NULL, 'nihao'),
(38, 21, 25, '2021-04-11 04:55:56', NULL, NULL, 'nihao'),
(39, 21, 25, '2021-04-11 04:58:41', NULL, NULL, '12点58分'),
(40, 25, 21, '2021-04-11 05:19:45', NULL, NULL, '13点19分'),
(41, 21, 21, '2021-04-11 05:20:10', NULL, NULL, '行不行13点20分'),
(42, 25, 21, '2021-04-11 05:32:34', NULL, NULL, '13点32分wohuohuo'),
(43, 21, 25, '2021-04-11 05:32:57', NULL, NULL, 'power');

-- --------------------------------------------------------

--
-- 表的结构 `normal_msg_offline`
--

CREATE TABLE `normal_msg_offline` (
  `id` bigint(32) NOT NULL COMMENT '离线消息号',
  `msgid` bigint(32) NOT NULL COMMENT '对应normal_msg的消息号',
  `NMO_toid` bigint(32) NOT NULL COMMENT '上线后要接收的用户',
  `NMO_state` tinyint(4) DEFAULT NULL COMMENT '发送的状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` bigint(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(8, 'nihao', 'haha'),
(20, 'nihaxo', 'haha'),
(21, 'username', 'password'),
(25, 'username1', 'password1');

--
-- 转储表的索引
--

--
-- 表的索引 `friend`
--
ALTER TABLE `friend`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `group_info`
--
ALTER TABLE `group_info`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `group_members`
--
ALTER TABLE `group_members`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `group_msg`
--
ALTER TABLE `group_msg`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `group_msg_offline`
--
ALTER TABLE `group_msg_offline`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `normal_msg`
--
ALTER TABLE `normal_msg`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `normal_msg_offline`
--
ALTER TABLE `normal_msg_offline`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `friend`
--
ALTER TABLE `friend`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `group_info`
--
ALTER TABLE `group_info`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '群组信息的id', AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `group_members`
--
ALTER TABLE `group_members`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=13;

--
-- 使用表AUTO_INCREMENT `group_msg`
--
ALTER TABLE `group_msg`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `group_msg_offline`
--
ALTER TABLE `group_msg_offline`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '离线消息号', AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `normal_msg`
--
ALTER TABLE `normal_msg`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '信息的id', AUTO_INCREMENT=44;

--
-- 使用表AUTO_INCREMENT `normal_msg_offline`
--
ALTER TABLE `normal_msg_offline`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '离线消息号';

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
