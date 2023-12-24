/*
 Navicat Premium Data Transfer

 Source Server         : sb
 Source Server Type    : SQL Server
 Source Server Version : 15002000
 Source Catalog        : educationsystem
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002000
 File Encoding         : 65001

 Date: 19/12/2023 09:10:32
*/


-- ----------------------------
-- Table structure for lesson
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[lesson]') AND type IN ('U'))
	DROP TABLE [dbo].[lesson]
GO

CREATE TABLE [dbo].[lesson] (
  [id] int IDENTITY(1,1) not NULL,
  [cource_id] int  NULL,
  [classroom_id] int  NULL,
  [teacher_id] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [term] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[lesson] SET (LOCK_ESCALATION = TABLE)
GO

