/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.FileInputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;

/**
 *
 * @author thuhu
 */
public class Utils {

    public int MP3DurationCalculator(String file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            Bitstream bitstream = new Bitstream(fis);
            Header header = bitstream.readFrame();

            int totalFrames = 0;
            float msPerFrame = header.ms_per_frame(); // Thời gian mỗi frame tính bằng ms

            while (header != null) {
                totalFrames++;
                bitstream.closeFrame();
                header = bitstream.readFrame();
            }

            int durationInSeconds = Math.round((totalFrames * msPerFrame) / 1000); // Tính tổng giây
            System.out.println("Duration: " + durationInSeconds + " seconds");
            return durationInSeconds;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static void main(String[] args) {
        Utils u = new Utils();
       u.MP3DurationCalculator("D:/0_FU_learning/SP2025/PRM392/MusicApplicationTemplate/MusicApplicationAndroid/List Music/MatKetNoi-DuongDomic-16783113.mp3");
    }
}
