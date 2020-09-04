package com.google.mlkit.vision.demo;

import android.graphics.Bitmap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ApiService {

    public static void saveFaceWithName(@Nullable Bitmap faceBitmap, @Nullable String name, @NotNull Function1<? super Boolean, Unit> onFinish) {
        // TODO: 9/3/2020 call the web service
        onFinish.invoke(true);
    }
}
