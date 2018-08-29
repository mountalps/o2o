package com.imooc.o2o.util;

import com.imooc.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * transfer CommonsMultipartFile to File
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){

        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        }catch (IllegalStateException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * deal with thumbnails and return relative address of created picture
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) throws RuntimeException{
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is: "+relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is: "+ PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails
                    .of(thumbnail.getImage())
                    .size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
            throw new RuntimeException("add water mark fails");
        }

        return relativeAddr;
    }

    /**
     * create directory related to target directory:
     * if the target directory is: /home/work/xiangze/xxx.jpg
     * we have to create these three folders: home, work, and xiangze
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * get the extension name of input file stream
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * generate random file name:
     * current year month day hour minute second + five-digit random number
     * @return
     */
    public static String getRandomFileName() {
        //get a five-digit random number
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    public static void main(String[] args) throws IOException {

        Thumbnails
                .of(new File("/Users/wesley/Pictures/flower.jpg"))
                .size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO
                        .read(new File(basePath + "/watermark.jpg")),0.25f)
                .outputQuality(0.8f)
                .toFile("/Users/wesley/Pictures/flowernew.jpg");
    }

    /**
     * if storePath is a file's path, then delete this file
     * if storePath is a directory, then delete this directory
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){

        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i ++){
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    /**
     * deal with images, and return new image's relative path
     *
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        // get a unique name
        String realFileName = getRandomFileName();
        // get the extension of the file name, like png, jpg
        String extension = getFileExtension(thumbnail.getImageName());
        // if the directory doesn't exist, create it automatically
        makeDirPath(targetAddr);
        // get the relative path of the file including the extension name
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        // get the target path to which the file save
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        // generate images with thumbnail
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("Thumbnail creation fails：" + e.toString());
        }
        // return the image's relative path
        return relativeAddr;
    }
}
