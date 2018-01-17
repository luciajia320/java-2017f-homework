package GroundDollMaven.testGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Write Files!
 *
 */
public class App  
{  
	public static boolean saveRecordInFile(String str,String path) {
        File record = new File(path);//记录结果文件
        try {
            if (!record.exists()) {

                File dir = new File(record.getParent());
                dir.mkdirs();
                record.createNewFile();
            }
            FileWriter writer = null;
            try {
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                writer = new FileWriter(record, false);
                writer.write(str);
                System.out.println("Succeed!");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Can not save logs!");
            return false;
        }
    }
  
}
