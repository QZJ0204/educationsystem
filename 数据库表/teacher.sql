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

 Date: 19/12/2023 09:14:16
*/


-- ----------------------------
-- Table structure for teacher
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[teacher]') AND type IN ('U'))
	DROP TABLE [dbo].[teacher]
GO

CREATE TABLE [dbo].[teacher] (
  [id] int  NOT NULL,
  [t_name] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [phone] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [sex] varchar(2) COLLATE Chinese_PRC_CI_AS  NULL,
  [endata] date  NULL,
  [salary] varchar(10) COLLATE Chinese_PRC_CI_AS  NULL,
  [pro] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [college_id] int  NULL
)
GO

ALTER TABLE [dbo].[teacher] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Primary Key structure for table teacher
-- ----------------------------
ALTER TABLE [dbo].[teacher] ADD CONSTRAINT [PK_teacher] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

