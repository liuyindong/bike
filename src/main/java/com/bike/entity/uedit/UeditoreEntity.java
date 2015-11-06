package com.bike.entity.uedit;

/**
 * Created by Administrator on 2015/1/31.
 */
public class UeditoreEntity
{
    /* 上传图片配置项 */
    private String imageActionName = "uploadimage";/* 执行上传图片的action名称 */
    private int imageMaxSize = 2048000; /* 上传大小限制，单位B */
    private String[] imageAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp"}; /* 上传图片格式显示 */
    private boolean imageCompressEnable = true;/* 是否压缩图片,默认是true */
    private int imageCompressBorder=1600; /* 图片压缩最长边限制 */
    private String imageInsertAlign="none";/* 插入的图片浮动方式 */
    private String  imageUrlPrefix="";/* 图片访问路径前缀 */
    private String imagePathFormat="/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";

     /* 涂鸦图片上传配置项 */
    private String scrawlActionName="uploadscrawl";/* 执行上传涂鸦的action名称 */
    private String scrawlFieldName="upfile";/* 提交的图片表单名称 */
    private String scrawlPathFormat="/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";/* 上传保存路径,可以自定义保存路径和文件名格式 */
    private int scrawlMaxSize=2048000;/* 上传大小限制，单位B */

    private String scrawlUrlPrefix="";/* 图片访问路径前缀 */

    private String scrawlInsertAlign = "none";

    /* 截图工具上传 */
    private String snapscreenActionName = "uploadimage";/* 执行上传截图的action名称 */
    private String snapscreenPathFormat = "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";/* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String snapscreenUrlPrefix = "";/* 图片访问路径前缀 */
    private String snapscreenInsertAlign = "none";/* 插入的图片浮动方式 */

    /* 抓取远程图片配置 */
    private String[] catcherLocalDomain = {"127.0.0.1", "localhost", "img.baidu.com"};
    private String catcherActionName = "catchimage";/* 执行抓取远程图片的action名称 */
    private String catcherFieldName = "source";/* 提交的图片列表表单名称 */
    private String catcherPathFormat = "/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}";/* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String catcherUrlPrefix = "";/* 图片访问路径前缀 */
    private int catcherMaxSize = 2048000;/* 上传大小限制，单位B */
    private String[] catcherAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};/* 抓取图片格式显示 */

    /* 上传视频配置 */
    private String videoActionName = "uploadvideo";/* 执行上传视频的action名称 */
    private String videoFieldName = "upfile";/* 提交的视频表单名称 */
    private String videoPathFormat = "/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}";/* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String videoUrlPrefix = "";/* 视频访问路径前缀 */
    private int videoMaxSize = 102400000;/* 上传大小限制，单位B，默认100MB */
    private String[] videoAllowFiles = {".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"};/* 上传视频格式显示 */

    /* 上传文件配置 */
    private String fileActionName = "uploadfile";/* controller里,执行上传视频的action名称 */
    private String fileFieldName = "upfile";/* 提交的文件表单名称 */
    private String filePathFormat = "/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}";/* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String fileUrlPrefix = "";/* 文件访问路径前缀 */
    private int fileMaxSize = 51200000;/* 上传大小限制，单位B，默认50MB */
    private String[] fileAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid", ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"};/* 上传文件格式显示 */

    /* 列出指定目录下的图片 */
    private String imageManagerActionName = "listimage";/* 执行图片管理的action名称 */
    private String imageManagerListPath = "/ueditor/jsp/upload/image/";/* 指定要列出图片的目录 */
    private int imageManagerListSize = 20;/* 每次列出文件数量 */
    private String imageManagerUrlPrefix = "";/* 图片访问路径前缀 */
    private String imageManagerInsertAlign = "none";/* 插入的图片浮动方式 */
    private String[] imageManagerAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};/* 列出的文件类型 */

    /* 列出指定目录下的文件 */
    private String fileManagerActionName = "listfile";/* 执行文件管理的action名称 */
    private String fileManagerListPath = "/ueditor/jsp/upload/file/";/* 指定要列出文件的目录 */
    private String fileManagerUrlPrefix = "";/* 文件访问路径前缀 */
    private int fileManagerListSize = 20;/* 每次列出文件数量 */
    private String[] fileManagerAllowFiles = {".png", ".jpg", ".jpeg", ".gif", ".bmp",".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"}; /* 列出的文件类型 */


    public String getImageActionName() {
        return imageActionName;
    }

    public int getImageMaxSize() {
        return imageMaxSize;
    }

    public String[] getImageAllowFiles() {
        return imageAllowFiles;
    }

    public boolean isImageCompressEnable() {
        return imageCompressEnable;
    }

    public int getImageCompressBorder() {
        return imageCompressBorder;
    }

    public String getImageInsertAlign() {
        return imageInsertAlign;
    }

    public String getImageUrlPrefix() {
        return imageUrlPrefix;
    }

    public String getImagePathFormat() {
        return imagePathFormat;
    }

    public String getScrawlActionName() {
        return scrawlActionName;
    }

    public String getScrawlFieldName() {
        return scrawlFieldName;
    }

    public String getScrawlPathFormat() {
        return scrawlPathFormat;
    }

    public int getScrawlMaxSize() {
        return scrawlMaxSize;
    }

    public String getScrawlUrlPrefix() {
        return scrawlUrlPrefix;
    }

    public String getScrawlInsertAlign() {
        return scrawlInsertAlign;
    }

    public String getSnapscreenActionName() {
        return snapscreenActionName;
    }

    public String getSnapscreenPathFormat() {
        return snapscreenPathFormat;
    }

    public String getSnapscreenUrlPrefix() {
        return snapscreenUrlPrefix;
    }

    public String getSnapscreenInsertAlign() {
        return snapscreenInsertAlign;
    }

    public String[] getCatcherLocalDomain() {
        return catcherLocalDomain;
    }

    public String getCatcherActionName() {
        return catcherActionName;
    }

    public String getCatcherFieldName() {
        return catcherFieldName;
    }

    public String getCatcherPathFormat() {
        return catcherPathFormat;
    }

    public String getCatcherUrlPrefix() {
        return catcherUrlPrefix;
    }

    public int getCatcherMaxSize() {
        return catcherMaxSize;
    }

    public String[] getCatcherAllowFiles() {
        return catcherAllowFiles;
    }

    public String getVideoActionName() {
        return videoActionName;
    }

    public String getVideoFieldName() {
        return videoFieldName;
    }

    public String getVideoPathFormat() {
        return videoPathFormat;
    }

    public String getVideoUrlPrefix() {
        return videoUrlPrefix;
    }

    public int getVideoMaxSize() {
        return videoMaxSize;
    }

    public String[] getVideoAllowFiles() {
        return videoAllowFiles;
    }

    public String getFileActionName() {
        return fileActionName;
    }

    public String getFileFieldName() {
        return fileFieldName;
    }

    public String getFilePathFormat() {
        return filePathFormat;
    }

    public String getFileUrlPrefix() {
        return fileUrlPrefix;
    }

    public int getFileMaxSize() {
        return fileMaxSize;
    }

    public String[] getFileAllowFiles() {
        return fileAllowFiles;
    }

    public String getImageManagerActionName() {
        return imageManagerActionName;
    }

    public String getImageManagerListPath() {
        return imageManagerListPath;
    }

    public int getImageManagerListSize() {
        return imageManagerListSize;
    }

    public String getImageManagerUrlPrefix() {
        return imageManagerUrlPrefix;
    }

    public String getImageManagerInsertAlign() {
        return imageManagerInsertAlign;
    }

    public String[] getImageManagerAllowFiles() {
        return imageManagerAllowFiles;
    }

    public String getFileManagerActionName() {
        return fileManagerActionName;
    }

    public String getFileManagerListPath() {
        return fileManagerListPath;
    }

    public String getFileManagerUrlPrefix() {
        return fileManagerUrlPrefix;
    }

    public int getFileManagerListSize() {
        return fileManagerListSize;
    }

    public String[] getFileManagerAllowFiles() {
        return fileManagerAllowFiles;
    }
}
