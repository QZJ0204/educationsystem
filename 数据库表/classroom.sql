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

 Date: 19/12/2023 09:03:13
*/


-- ----------------------------
-- Table structure for classroom
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[classroom]') AND type IN ('U'))
	DROP TABLE [dbo].[classroom]
GO

CREATE TABLE [dbo].[classroom] (
  [id] int  IDENTITY(202101,1) NOT NULL,
  [cl_name] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [teacher_id] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [college_id] int  NULL
)
GO

ALTER TABLE [dbo].[classroom] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Primary Key structure for table classroom
-- ----------------------------
ALTER TABLE [dbo].[classroom] ADD CONSTRAINT [PK_classroom] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

