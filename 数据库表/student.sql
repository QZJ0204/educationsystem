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

 Date: 19/12/2023 09:14:10
*/


-- ----------------------------
-- Table structure for student
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[student]') AND type IN ('U'))
	DROP TABLE [dbo].[student]
GO

CREATE TABLE [dbo].[student] (
  [id] varchar(10) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [home] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [phone] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [sex] varchar(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [birth] date  NULL,
  [endata] date  NULL,
  [class_id] int  NULL
)
GO

ALTER TABLE [dbo].[student] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Primary Key structure for table student
-- ----------------------------
ALTER TABLE [dbo].[student] ADD CONSTRAINT [PK_student] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

