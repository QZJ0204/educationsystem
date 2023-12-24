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

 Date: 19/12/2023 09:09:29
*/


-- ----------------------------
-- Table structure for cource
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cource]') AND type IN ('U'))
	DROP TABLE [dbo].[cource]
GO

CREATE TABLE [dbo].[cource] (
  [id] int IDENTITY(1001,1) not NULL,
  [c_name] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [type] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [grade] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[cource] SET (LOCK_ESCALATION = TABLE)
GO

