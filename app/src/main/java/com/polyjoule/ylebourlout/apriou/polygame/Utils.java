package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by Alexis on 14/02/2018.
 */


public class Utils {
    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static String streamToString(InputStream is) throws IOException {
        String str = "";

        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
            } finally {
                is.close();
            }

            str = sb.toString();
        }

        return str;
    }

    /**
     * Helper method to calculate the proper size limit of a cache instance.
     */
    public static long getCacheSizeInBytes(File dir, float cacheSizePercent, long maxSizeInBytes) {
        if (dir == null || (!dir.exists() && !dir.mkdir())) {
            return 0;
        }
        try {
            StatFs stat = new StatFs(dir.getPath());
            long totalBytes = stat.getBlockCountLong() * stat.getBlockSizeLong();
            long freeBytes = stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
            long desiredBytes = Math.min((long) (totalBytes * cacheSizePercent), maxSizeInBytes);
            // If free space is less than desired, use half of the free disk space instead.
            desiredBytes = (desiredBytes > freeBytes) ? freeBytes / 2 : desiredBytes;
            return desiredBytes;
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    /**
     * Helper method to initiate cache directory. It will return the cache directory in File format,
     * or NULL if the directory path is invalid or not accessible.
     */
    public static File getCacheDirectory(final Context context, final String path) {
        File cacheDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                cacheDir = context.getExternalCacheDir();
            } catch (NullPointerException e) {
                // Fallback to use internal storage if external storage isn't available.
            }
        }
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        return (cacheDir != null && path != null) ? new File(cacheDir, path) : null;
    }

    /**
     * Helper method to close a Closeable (e.g. InputStream/OutputStream) quietly without throwing any
     * additional IOExceptions.
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Handle the IOException quietly.
            }
        }
    }

}

