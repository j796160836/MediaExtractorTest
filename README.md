# MediaExtractorTest
For report bug https://code.google.com/p/android/issues/detail?id=203913

---

I use Android N Developer preview (NPC56P) and encounter MediaExtractor sends signal 6 (SIGABRT) error when use this method below.

`public final void setDataSource (FileDescriptor fd)`  
http://developer.android.com/intl/zh-tw/reference/android/media/MediaExtractor.html#setDataSource(java.io.FileDescriptor)
  
This method is okay  
`public final void setDataSource (String path)`  
http://developer.android.com/intl/zh-tw/reference/android/media/MediaExtractor.html#setDataSource(java.lang.String)

---

Error log   
```
Fatal signal 6 (SIGABRT), code -6 in tid 9456 (mediaextractor)
 *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
 Build fingerprint: 'google/angler/angler:N/NPC56P/2659757:user/release-keys'
 Revision: '0'
 ABI: 'arm'
 pid: 9456, tid: 9456, name: mediaextractor  >>> media.extractor <<<
 signal 6 (SIGABRT), code -6 (SI_TKILL), fault addr --------
     r0 00000000  r1 000024f0  r2 00000006  r3 00000008
     r4 e9a98574  r5 00000006  r6 e9a9851c  r7 0000010c
     r8 e8b97330  r9 e8b97328  sl e8b97300  fp 00000000
     ip 00000016  sp ffc1d460  lr e8fbda47  pc e8fc02a4  cpsr 200f0010
 backtrace:
     #00 pc 000492a4  /system/lib/libc.so (tgkill+12)
     #01 pc 00046a43  /system/lib/libc.so (pthread_kill+34)
     #02 pc 0001d461  /system/lib/libc.so (raise+10)
     #03 pc 00019029  /system/lib/libc.so (__libc_android_abort+34)
     #04 pc 00016e20  /system/lib/libc.so (abort+4)
     #05 pc 0009a58d  /system/lib/libstagefright.so (android::MP3Extractor::MP3Extractor(android::sp<android::DataSource> const&, android::sp<android::AMessage> const&)+1240)
     #06 pc 000c2063  /system/lib/libstagefright.so (android::MediaExtractor::CreateFromService(android::sp<android::DataSource> const&, char const*)+406)
     #07 pc 00002707  /system/lib/libmediaextractorservice.so (android::MediaExtractorService::makeExtractor(android::sp<android::IDataSource> const&, char const*)+34)
     #08 pc 000840ab  /system/lib/libmedia.so (android::BnMediaExtractorService::onTransact(unsigned int, android::Parcel const&, android::Parcel*, unsigned int)+90)
     #09 pc 000358e3  /system/lib/libbinder.so (android::BBinder::transact(unsigned int, android::Parcel const&, android::Parcel*, unsigned int)+70)
     #10 pc 0003d08d  /system/lib/libbinder.so (android::IPCThreadState::executeCommand(int)+712)
     #11 pc 0003cd85  /system/lib/libbinder.so (android::IPCThreadState::getAndExecuteCommand()+80)
     #12 pc 0003d1e7  /system/lib/libbinder.so (android::IPCThreadState::joinThreadPool(bool)+46)
     #13 pc 00000f91  /system/bin/mediaextractor
     #14 pc 00016a39  /system/lib/libc.so (__libc_init+48)
     #15 pc 00000e54  /system/bin/mediaextractor
                                        
 service 'media.extractor' died
 java.io.IOException: Failed to instantiate extractor.
     at android.media.MediaExtractor.setDataSource(Native Method)
     at android.media.MediaExtractor.setDataSource(MediaExtractor.java:198)
     at com.johnny.mediaextractor.MediaExtractorHelper.parseFile(MediaExtractorHelper.java:31)
     at com.johnny.mediaextractor.MainActivity$1.onClick(MainActivity.java:31)
     at android.view.View.performClick(View.java:5565)
     at android.view.View$PerformClick.run(View.java:22047)
     at android.os.Handler.handleCallback(Handler.java:739)
     at android.os.Handler.dispatchMessage(Handler.java:95)
     at android.os.Looper.loop(Looper.java:148)
     at android.app.ActivityThread.main(ActivityThread.java:5849)
     at java.lang.reflect.Method.invoke(Native Method)
     at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:763)
     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:653)
```
