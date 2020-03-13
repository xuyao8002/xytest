package com.xuyao.test.other.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xuyao.test.encrypt.Base64Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {

    public static void main(String[] args) throws Exception {
        String qrCode = createQRCode("hello", null, null, null);
        final byte[] bytes = Base64Util.decode1(qrCode);
        final Result result = readQRcode(new ByteArrayInputStream(bytes));
        System.out.println(result.getText());
    }

    /**
     * 读取二维码对象
     * @param inputStream
     * @return
     */
    public static Result readQRcode(InputStream inputStream) throws Exception {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        BufferedImage image = ImageIO.read(inputStream);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        return multiFormatReader.decode(binaryBitmap);
    }

    /**
     * 设置logo
     * @param matrixImage
     * @param logUri
     * @return
     * @throws IOException
     */
    public static BufferedImage setMatrixLogo(BufferedImage matrixImage,String logUri) throws IOException{
        Graphics2D g2 = matrixImage.createGraphics();
        int matrixWidth = matrixImage.getWidth();
        int matrixHeigh = matrixImage.getHeight();
        BufferedImage logo = ImageIO.read(new File(logUri));
        //开始绘制图片
        g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);
        //绘制边框
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        // 设置笔画对象
        g2.setStroke(stroke);
        //指定弧度的圆角矩形
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);
        g2.setColor(Color.white);
        // 绘制圆弧矩形
        g2.draw(round);

        //设置logo 有一道灰色边框
        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke2);
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);
        g2.setColor(new Color(128,128,128));
        g2.draw(round2);
        g2.dispose();

        matrixImage.flush() ;
        return matrixImage ;

    }

    /**
     * 生成二维码
     * @param contents 内容
     * @param width 宽度
     * @param height 高度
     * @param format 格式
     * @return base64编码值
     */
    public static String createQRCode(String contents, Integer width, Integer height, String format) throws Exception {
        if (width == null) {
            width = 300;
        }
        if (height == null) {
            height = 300;
        }
        if (format == null) {
            format = "png";
        }
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 设置字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);
        // 生成二维码矩阵
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage bufferedImage = toBufferedImage(bitMatrix);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, stream);
        return Base64Util.encode1(stream.toByteArray());
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF));
            }
        }
        return image;
    }

}
