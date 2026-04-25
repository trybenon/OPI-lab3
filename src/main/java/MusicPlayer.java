import javax.sound.sampled.*;
        import java.io.File;

public class MusicPlayer {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("[MUSIC] Файл не указан.");
            return;
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("[MUSIC] Файл не найден: " + file.getAbsolutePath());
            return;
        }
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)) {
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("[MUSIC] Аудио не поддерживается.");
                return;
            }
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
            System.out.println("[MUSIC] Играет: " + file.getName());
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.stop();
            clip.close();
        } catch (Exception e) {
            System.out.println("[MUSIC] Ошибка: " + e.getMessage());
        }
    }
}