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

 Date: 19/12/2023 09:07:54
*/


-- ----------------------------
-- Table structure for college
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[college]') AND type IN ('U'))
	DROP TABLE [dbo].[college]
GO

CREATE TABLE [dbo].[college] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [name] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL,
  [teacher] varchar(30) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[college] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of [college]
-- ----------------------------
INSERT INTO [dbo].[college]  VALUES (N'1', N'数学与计算机科学学学院', N'张三')
GO

INSERT INTO [dbo].[college]  VALUES (N'2', N'马克思主义学院', N'李四')
GO

INSERT INTO [dbo].[college]  VALUES (N'3', N'音乐与舞蹈学院', N'王五')
GO

INSERT INTO [dbo].[college]  VALUES (N'4', N'体育学院', N'赵六')
GO

INSERT INTO [dbo].[college]  VALUES (N'5', N'文化与传播学院', N'五七')
GO

INSERT INTO [dbo].[college]  VALUES (N'6', N'书法学院', N'张泽')
GO

INSERT INTO [dbo].[college]  VALUES (N'7', N'机械与电气学院', N'巴柏')
GO

INSERT INTO [dbo].[college]  VALUES (N'8', N'小学教育学院', N'张炳')
GO


-- ----------------------------
-- Primary Key structure for table college
-- ----------------------------
ALTER TABLE [dbo].[college] ADD CONSTRAINT [PK_college] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

