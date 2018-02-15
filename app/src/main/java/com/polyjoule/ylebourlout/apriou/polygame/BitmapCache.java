//package com.polyjoule.ylebourlout.apriou.polygame;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Log;
//import android.util.LruCache;
//
//import java.io.File;
//import java.io.FileDescriptor;
//import java.io.IOException;
//
///**
// * Created by Alexis on 14/02/2018.
// */
//
//public class BitmapCache {
//    private static final String TAG = BitmapCache.class.getSimpleName();
//    private static final String DISK_CACHE_DIR = "bitmap";
//    private static final int DEFAULT_MEM_CACHE_CAP = 10;
//    private static final int DEFAULT_DISK_CACHE_SIZE = 1024 * 1024 * 100; // 100MB
//    private static final int DEFAULT_DISK_CACHE_SIZE_PERCENT = 10; // 10% of free disk space
//
//    private Context mContext;
//    private IgDiskCache mDiskCache;
//    private LruCache<String, Bitmap> mMemoryCache;
//
//    public BitmapCache(Context context) {
//        mContext = context;
//        mMemoryCache = new LruCache<>(DEFAULT_MEM_CACHE_CAP);
//    }
//
//    /**
//     * Cache Bitmap both in memory and on disk. The bitmap will be compressed into JPEG for disk
//     * storage.
//     */
//    public void addBitmapToCache(String key, Bitmap bitmap) {
//        if (key == null || bitmap == null) {
//            return;
//        }
//        mMemoryCache.put(key, bitmap);
//        if (!getDiskCache().has(key)) {
//            OptionalStream<EditorOutputStream> output = getDiskCache().edit(key);
//            if (output.isPresent()) {
//                try {
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, output.get());
//                    output.get().commit();
//                } catch (Exception e) {
//                    Log.e(TAG, "addBitmapToCache - " + e);
//                } finally {
//                    output.get().abortUnlessCommitted();
//                }
//            }
//        }
//    }
//
//    /**
//     * Get the decoded Bitmap from memory cache
//     */
//    public Bitmap getBitmapFromMemCache(String key) {
//        return mMemoryCache.get(key);
//    }
//
//    /**
//     * Get the decoded Bitmap from disk cache
//     */
//    public Bitmap getBitmapFromDiskCache(String key) {
//        Bitmap bitmap = null;
//        OptionalStream<SnapshotInputStream> input = getDiskCache().get(key);
//        if (input.isPresent()) {
//            try {
//                FileDescriptor fd = input.get().getFD();
//                bitmap = BitmapFactory.decodeFileDescriptor(fd);
//            } catch (IOException e) {
//                Log.e(TAG, "getBitmapFromDiskCache - " + e);
//            } finally {
//                Utils.closeQuietly(input.get());
//            }
//        }
//        return bitmap;
//    }
//
//    /**
//     * Flush the disk cache used for storing Bitmaps
//     */
//    public void flush() {
//        getDiskCache().flush();
//    }
//
//    /**
//     * Close the disk cache used for storing Bitmaps
//     */
//    public void close() {
//        getDiskCache().close();
//    }
//
//    private IgDiskCache getDiskCache() {
//        // lazy initialization of IgDiskCache to avoid calling it from the main UI thread.
//        if (mDiskCache == null) {
//            File cacheDir = Utils.getCacheDirectory(mContext, DISK_CACHE_DIR);
//            mDiskCache = new IgDiskCache(
//                    cacheDir,
//                    Utils.getCacheSizeInBytes(
//                            cacheDir,
//                            DEFAULT_DISK_CACHE_SIZE_PERCENT / 100,
//                            DEFAULT_DISK_CACHE_SIZE)
//            );
//        }
//        return mDiskCache;
//    }
//}
