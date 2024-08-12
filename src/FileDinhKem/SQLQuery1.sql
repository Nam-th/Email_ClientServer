USE [De10]
GO
/****** Object:  Table [dbo].[EmailAttachments]    Script Date: 10/21/2023 12:30:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmailAttachments](
	[attachment_id] [int] IDENTITY(1,1) NOT NULL,
	[email_id] [int] NOT NULL,
	[file_name] [varchar](255) NOT NULL,
	[file_data] [varbinary](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[attachment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Emails]    Script Date: 10/21/2023 12:30:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Emails](
	[email_id] [int] IDENTITY(1,1) NOT NULL,
	[subject] [varchar](255) NULL,
	[body] [text] NULL,
	[sender_id] [int] NOT NULL,
	[timestamp] [datetime] NOT NULL,
	[is_read] [int] NULL,
	[is_spam] [int] NULL,
	[is_deleted] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[email_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recipients]    Script Date: 10/21/2023 12:30:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recipients](
	[recipient_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[email_id] [int] NOT NULL,
	[is_cc] [int] NULL,
	[is_bcc] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[recipient_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 10/21/2023 12:30:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](255) NOT NULL,
	[full_name] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[email_quota] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [unique_username] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Emails] ADD  DEFAULT ((0)) FOR [is_read]
GO
ALTER TABLE [dbo].[Emails] ADD  DEFAULT ((0)) FOR [is_spam]
GO
ALTER TABLE [dbo].[Emails] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[Recipients] ADD  DEFAULT ((0)) FOR [is_cc]
GO
ALTER TABLE [dbo].[Recipients] ADD  DEFAULT ((0)) FOR [is_bcc]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((100)) FOR [email_quota]
GO
ALTER TABLE [dbo].[EmailAttachments]  WITH CHECK ADD  CONSTRAINT [fk_email_attachment] FOREIGN KEY([email_id])
REFERENCES [dbo].[Emails] ([email_id])
GO
ALTER TABLE [dbo].[EmailAttachments] CHECK CONSTRAINT [fk_email_attachment]
GO
ALTER TABLE [dbo].[Emails]  WITH CHECK ADD  CONSTRAINT [fk_sender] FOREIGN KEY([sender_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Emails] CHECK CONSTRAINT [fk_sender]
GO
ALTER TABLE [dbo].[Recipients]  WITH CHECK ADD  CONSTRAINT [fk_email] FOREIGN KEY([email_id])
REFERENCES [dbo].[Emails] ([email_id])
GO
ALTER TABLE [dbo].[Recipients] CHECK CONSTRAINT [fk_email]
GO
ALTER TABLE [dbo].[Recipients]  WITH CHECK ADD  CONSTRAINT [fk_user] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Recipients] CHECK CONSTRAINT [fk_user]
GO
