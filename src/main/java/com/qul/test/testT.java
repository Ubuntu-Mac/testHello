package com.qul.test;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.*;

/**
 * Created by yanfazhongxin on 2019/6/11.
 */
public class testT extends FileAlterationListenerAdaptor {

    public File DirContext;

    public testT(File dirContext) {
        super();
        DirContext = dirContext;
    }

    //文件创建    
    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
        if (file.isFile()){
            if (file.getName().contains(".war")){
                String version = getVersion();
                Integer integer = Integer.valueOf(version);
                String newVersion = (integer+1)+"";
                Writer writer=null;
                try {
                    writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\MyCode\\osms-admin-and-front\\osms-admin-and-front\\version.txt"), "UTF-8"));
                    writer.write(newVersion);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        File dir = new File("D:\\MyCode\\osms-admin-and-front\\osms-admin-and-front\\target");
        FileAlterationMonitor monitor = new FileAlterationMonitor();
        IOFileFilter filter = FileFilterUtils.or(FileFilterUtils.directoryFileFilter(),FileFilterUtils.fileFileFilter());

        FileAlterationObserver observer = new FileAlterationObserver(dir,filter);
        observer.addListener(new testT(dir));

        monitor.addObserver(observer);
        try {
            //开始监听    
            monitor.start();
            System.out.println("文件监听……");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从配置文件读取
     * @return
     */
    public static String getVersion(){
        BufferedReader br = null;
        try {
            br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\MyCode\\osms-admin-and-front\\osms-admin-and-front\\version.txt"),"UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                return line;
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
