import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;

import java.io.FileInputStream;
//""
public class MP3DurationCalculator {
    public static void main(String[] args) {
        String filePath = "path/to/your/music.mp3";

        try (FileInputStream fis = new FileInputStream(filePath)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}