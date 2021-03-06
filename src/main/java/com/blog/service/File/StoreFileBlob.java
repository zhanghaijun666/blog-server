package com.blog.service.File;

import com.blog.utils.EncryptUtils;
import com.blog.utils.PathUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreFileBlob extends StoreFile {
    private static final int MAX_BLOB_SIZE = 1 * 1024 * 1024;

    private static File getHashFile(String hash) {
        return new File(PathUtils.joinPath(StoreFileBlob.getStorePath(), "blob", StoreFileBlob.getHashDir(hash)), hash);
    }

    public static List<String> writeFile(byte[] bytes) throws IOException {
        List<String> hashList = new ArrayList<>();
        int totalSize = bytes.length;
        int indexSize = 0;
        while (indexSize < totalSize) {
            byte[] blobBytes = Arrays.copyOfRange(bytes, indexSize, Math.min(indexSize + StoreFileBlob.MAX_BLOB_SIZE, totalSize));
            indexSize += StoreFileBlob.MAX_BLOB_SIZE;
            String hash = EncryptUtils.sha1(blobBytes);
            hashList.add(hash);
            File hashFile = StoreFileBlob.getHashFile(hash);
            if (!hashFile.getParentFile().exists()) {
                hashFile.getParentFile().mkdirs();
            }
            if (hashFile.exists()) {
                continue;
            }
            Files.write(hashFile.toPath(), blobBytes);
        }
        return hashList;
    }

    private static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    public static byte[] readFile(List<String> list) {
        byte[] result = new byte[]{};
        for (String hash : list) {
            File hashFile = StoreFileBlob.getHashFile(hash);
            try (InputStream in = new FileInputStream(hashFile)) {
                int len = -1;
                byte[] bytes = new byte[1 * 1024 * 1024];
                while ((len = in.read(bytes)) != -1) {
                    result = byteMerger(result, bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void readFile(List<String> list, OutputStream out) {
        for (String hash : list) {
            File hashFile = StoreFileBlob.getHashFile(hash);
            try (InputStream in = new FileInputStream(hashFile)) {
                int len = -1;
                byte[] bytes = new byte[1 * 1024 * 1024];
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
