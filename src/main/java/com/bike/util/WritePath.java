package com.bike.util;


import java.io.*;

public class WritePath
{
    /**
     * 读取文本文件.
     *
     */
    public static String readTxtFile(String path)
    {
        BufferedReader bufread;
        // 指定文件路径和名称
        File filename = new File(path);
        String readStr = "";

        String read;
        FileReader fileread;
        try
        {
            fileread = new FileReader(filename);
            bufread = new BufferedReader(fileread);
            try
            {
                while ((read = bufread.readLine()) != null)
                {
                    readStr = readStr + read + "\r\n";
                    readStr = readStr.replaceAll("\\s*", "");
                }
                // System.out.println("读取到的内容是:"+readStr);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return readStr;
    }

    /**
     * 向文本文件中写入内容或追加新内容,如果append为true则直接追加新内容,<br>
     * 如果append为false则覆盖原来的内容<br>
     *
     * @param path
     *            路径
     * @param content
     *            内容
     * @param append
     */
    public static boolean writeFile(String path, String content, boolean append)
    {
        File writefile;
        try
        {
            // 通过这个对象来判断能不能 向文本文件中追加内容
            boolean addStr = append;

            writefile = new File(path);

            if (!writefile.isDirectory())
            { // 目录不存在
                writefile.getParentFile().mkdirs();
                writefile.createNewFile();
                writefile = new File(path); // 重新实例化
            }

            FileOutputStream fw = new FileOutputStream(writefile, addStr);

            // content = new String(content.getBytes(),"gbk");

            fw.write(content.getBytes());
            fw.flush();
            fw.close();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /** */
    /**
     * 转移文件目录
     *
     * @param filename
     *            文件名
     * @param oldpath
     *            旧目录
     * @param newpath
     *            新目录
     * @param cover
     *            若新目录下存在和转移文件具有相同文件名的文件时，是否覆盖新目录下文件，cover=true将会覆盖原文件，否则不操作
     */
    public static void changeDirectory(String filename, String oldpath, String newpath, boolean cover)
    {
        if (!oldpath.equals(newpath))
        {
            File oldfile = new File(oldpath + "/" + filename);
            File newfile = new File(newpath + "/" + filename);
            if (newfile.exists())
            {// 若在待转移目录下，已经存在待转移文件
                if (cover)// 覆盖
                    oldfile.renameTo(newfile);
                else
                    System.out.println("在新目录下已经存在：" + filename);
            }
            else
            {
                oldfile.renameTo(newfile);
            }
        }
    }

    /** */
    /**
     * 以文件流的方式复制文件
     *
     * @param src
     *            文件源目录
     * @param dest
     *            文件目的目录
     * @throws IOException
     */
    public void copyFile(String src, String dest) throws IOException
    {
        FileInputStream in = new FileInputStream(src);
        File file = new File(dest);
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int c;
        byte buffer[] = new byte[1024];
        while ((c = in.read(buffer)) != -1)
        {
            for (int i = 0; i < c; i++)
                out.write(buffer[i]);
        }
        in.close();
        out.close();
    }

    /** */
    /**
     * 获得控制台用户输入的信息
     *
     * @return
     * @throws IOException
     */
    public String getInputMessage() throws IOException
    {
        System.out.println("请输入您的命令∶");
        byte buffer[] = new byte[1024];
        int count = System.in.read(buffer);
        char[] ch = new char[count - 2];// 最后两位为结束符，删去不要
        for (int i = 0; i < count - 2; i++)
            ch[i] = (char) buffer[i];
        String str = new String(ch);
        return str;
    }

    /** */
    /**
     * 文件重命名
     *
     * @param path
     *            文件目录
     * @param oldname
     *            原来的文件名
     * @param newname
     *            新文件名
     */
    public void renameFile(String path, String oldname, String newname)
    {
        if (!oldname.equals(newname))
        {// 新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile = new File(path + "/" + oldname);
            File newfile = new File(path + "/" + newname);
            if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname + "已经存在！");
            else
            {
                oldfile.renameTo(newfile);
            }
        }
    }


    /** */
    /**
     * 创建文件夹
     *
     * @param path
     *            目录
     */
    public void createDir(String path)
    {
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdir();
    }

    /** */
    /**
     * 创建新文件
     *
     * @param path
     *            目录
     * @param filename
     *            文件名
     * @throws IOException
     */
    public void createFile(String path, String filename) throws IOException
    {
        File file = new File(path + "/" + filename);
        if (!file.exists())
            file.createNewFile();
    }

    /**
     *  删除文件
     * @param filePath
     */
    public void delFile(String filePath)
    {
        File file = new File(filePath);
        if (file.exists() && file.isFile())
            file.delete();
    }

    /** */
    /**
     * 递归删除文件夹
     *
     * @param path
     */
    public static void delDir(String path)
    {
        File dir = new File(path);
        if (dir.exists())
        {
            File[] tmp = dir.listFiles();

            System.out.println("删除了" + dir);

            for (int i = 0; i < tmp.length; i++)
            {
                if (tmp[i].isDirectory())
                {
                    delDir(path + "/" + tmp[i].getName());
                }
                else
                {
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }

    /**
     *
     */
    public static String zhuanHuanIp(String loginAddress)
    {
        String loginZuiHou = null;
        String loginPath = loginAddress.toString();
        if (loginPath.startsWith("/"))
        {
            loginZuiHou = loginPath.substring(1);
            loginZuiHou = loginZuiHou.replaceAll("\\.", "");
            loginZuiHou = loginZuiHou.replaceAll("\\:", "");
        }
        else
        {
            loginZuiHou = loginPath.replaceAll("\\.", "");
            loginZuiHou = loginZuiHou.replaceAll("\\:", "");
        }
        return loginZuiHou;
    }
}
