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

 Date: 19/12/2023 09:12:01
*/


-- ----------------------------
-- Table structure for selectcou
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[selectcou]') AND type IN ('U'))
	DROP TABLE [dbo].[selectcou]
GO

CREATE TABLE [dbo].[selectcou] (
  [s_id] int IDENTITY(1,1) NOT NULL,
  [cource_id] int  NULL,
  [student_id] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [teacher_id] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [score] int  NULL
)
GO

ALTER TABLE [dbo].[selectcou] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Primary Key structure for table selectcou
-- ----------------------------
ALTER TABLE [dbo].[selectcou] ADD CONSTRAINT [PK_selectcou] PRIMARY KEY CLUSTERED ([s_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

