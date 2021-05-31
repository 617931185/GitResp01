package com.mist;

import org.junit.Test;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;

public class ImageTest {
    @Test
    public void test(){
        try {
            BufferedImage image1 = ImageIO.read(new File("/Users/yexinhui/Downloads/tank-images/tankD.gif"));
            assertNotNull(image1);

            ImageIcon image2 = new ImageIcon(ImageTest.class.getResource("images/tankD.gif"));
            assertNotNull(image2);

            BufferedImage image3 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            assertNotNull(image3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
